SELECT p.id, despesa.valor, despesa.conta_financeira


FROM projeto p
JOIN (SELECT projeto_id,conta_financeira,  SUM(dp.realizado + dp.previsto) AS valor
	FROM despesa dp GROUP BY projeto_id, conta_financeira) as despesa
ON p.id = despesa.projeto_id