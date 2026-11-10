#!/usr/bin/env node

const fs = require("fs");
const path = require("path");

const dataDir = path.resolve(process.argv[2] || path.join(__dirname, "..", "dados-contoso-rh"));
const specs = {
  aprovacoes: [12, "AprovacaoId", "AprovacaoId,ReembolsoId,AprovadorMatricula,Nivel,Decisao,DataDecisao,Comentario"],
  beneficios: [13, "BeneficioId", "BeneficioId,Matricula,Tipo,Valor,DataAtivacao,Ativo"],
  colaboradores: [12, "Matricula", "Matricula,Nome,CPF,Cargo,Departamento,Pais,DataAdmissao,Salario,Status"],
  encargos: [14, "EncargoId", "EncargoId,Matricula,Competencia,Tipo,BaseCalculo,Valor"],
  ferias: [12, "FeriaId", "FeriaId,Matricula,DataInicio,DataFim,DiasGozados,SaldoDias,Status"],
  holerites: [14, "HoleriteId", "HoleriteId,Matricula,Competencia,SalarioBruto,Descontos,SalarioLiquido,DataPagamento"],
  irrf: [12, "IrrfId", "IrrfId,Matricula,Competencia,BaseCalculo,Aliquota,ValorRetido,DeducaoDependentes"],
  provisoes: [13, "ProvisaoId", "ProvisaoId,Matricula,Tipo,Competencia,ValorProvisionado,Acumulado"],
  reembolsos: [13, "ReembolsoId", "ReembolsoId,Matricula,Categoria,Valor,Moeda,DataDespesa,Status"],
  tickets: [12, "TicketId", "TicketId,Matricula,Categoria,Prioridade,DataAbertura,DataResolucao,Status"],
};

function parseCsv(text) {
  const records = [];
  let record = [];
  let field = "";
  let quoted = false;
  for (let index = 0; index < text.length; index += 1) {
    const char = text[index];
    if (char === '"') {
      if (quoted && text[index + 1] === '"') {
        field += '"';
        index += 1;
      } else quoted = !quoted;
    } else if (char === "," && !quoted) {
      record.push(field);
      field = "";
    } else if ((char === "\n" || char === "\r") && !quoted) {
      if (char === "\r" && text[index + 1] === "\n") index += 1;
      record.push(field);
      if (record.some(Boolean)) records.push(record);
      record = [];
      field = "";
    } else field += char;
  }
  if (quoted) throw new Error("CSV com aspas sem fechamento");
  if (field || record.length) {
    record.push(field);
    records.push(record);
  }
  return records;
}

function assert(condition, message) {
  if (!condition) throw new Error(message);
}

function load(name) {
  const text = fs.readFileSync(path.join(dataDir, `${name}.csv`), "utf8").replace(/^\uFEFF/, "");
  assert(!text.includes("\uFFFD"), `${name}.csv contem UTF-8 invalido`);
  const [headers, ...records] = parseCsv(text);
  const rows = records.map((record, index) => {
    assert(record.length === headers.length, `${name}.csv linha ${index + 2}: quantidade de campos invalida`);
    return Object.fromEntries(headers.map((header, column) => [header, record[column]]));
  });
  return { headers, rows };
}

function reference(tables, source, column, target, targetColumn) {
  const valid = new Set(tables[target].rows.map((row) => row[targetColumn]));
  const missing = tables[source].rows.filter((row) => !valid.has(row[column]));
  assert(missing.length === 0, `${source}.${column}: ${missing.length} referencia(s) orfa(s)`);
}

try {
  const tables = {};
  for (const [name, [count, key, schema]] of Object.entries(specs)) {
    const table = load(name);
    tables[name] = table;
    assert(table.headers.join(",") === schema, `${name}.csv: schema inesperado`);
    assert(table.rows.length === count, `${name}.csv: esperado ${count} linhas, recebido ${table.rows.length}`);
    const keys = table.rows.map((row) => row[key]);
    assert(keys.every(Boolean), `${name}.${key}: chave vazia`);
    assert(new Set(keys).size === keys.length, `${name}.${key}: chave duplicada`);
    console.log(`${name.padEnd(13)} ${String(count).padStart(2)} linhas  schema/chave OK`);
  }

  for (const source of ["beneficios", "encargos", "ferias", "holerites", "irrf", "provisoes", "reembolsos", "tickets"]) {
    reference(tables, source, "Matricula", "colaboradores", "Matricula");
  }
  reference(tables, "aprovacoes", "AprovadorMatricula", "colaboradores", "Matricula");
  reference(tables, "aprovacoes", "ReembolsoId", "reembolsos", "ReembolsoId");

  const isoDate = /^\d{4}-\d{2}-\d{2}$/;
  const period = /^\d{4}-\d{2}$/;
  for (const row of tables.holerites.rows) {
    assert(period.test(row.Competencia), `Competencia invalida: ${row.Competencia}`);
    assert(isoDate.test(row.DataPagamento), `DataPagamento invalida: ${row.DataPagamento}`);
    for (const column of ["SalarioBruto", "Descontos", "SalarioLiquido"]) {
      assert(row[column] !== "" && Number.isFinite(Number(row[column])), `${column} invalido em ${row.HoleriteId}`);
    }
  }

  const folhaIds = tables.holerites.rows.map((row) => `${row.HoleriteId}|${row.Matricula}|${row.Competencia}`);
  const periods = new Set(tables.holerites.rows.map((row) => row.Competencia));
  assert(new Set(folhaIds).size === folhaIds.length, "gold.fact_folha_pagamento.folha_id nao seria unica");
  assert(/[áéíóúãõçüöä]/i.test(tables.colaboradores.rows.map((row) => row.Nome).join("|")), "Nomes UTF-8 acentuados nao encontrados");

  console.log("Referencias Matricula/ReembolsoId OK");
  console.log(`Gold esperada: colaboradores=${tables.colaboradores.rows.length}, folha=${folhaIds.length}, periodos=${periods.size}`);
  console.log("LAB03_DATASET_OK");
} catch (error) {
  console.error(`LAB03_DATASET_FAILED: ${error.message}`);
  process.exitCode = 1;
}
