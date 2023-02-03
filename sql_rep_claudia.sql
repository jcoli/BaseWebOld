SELECT numero, sum(valor_despesa) as despesa, sum(valor_budget) as budget, conta	
FROM
 (( SELECT p.numero,
            0.00 AS valor_budget,
            COALESCE(despesa.valor_despesa, 0::real) AS valor_despesa,
            despesa.conta
           FROM projeto p
             JOIN ( SELECT dp.projeto_id AS id,
                    dp.conta_financeira AS conta,
                    sum(dp.realizado + dp.previsto) AS valor_despesa,
                    0.00 AS valor_budget
                   FROM despesa_financeiro dp
                 GROUP BY dp.projeto_id, conta) despesa ON p.id = despesa.id
             JOIN ( SELECT tcf.descricao
                   FROM table_conta_financeira tcf
                  ) cta ON despesa.conta::text = cta.descricao::text) 
        UNION ALL
	(SELECT p.numero,
            COALESCE(budget.valor_budget::real, 0::real) AS valor_budget,
            0.00 AS valor_despesa,
            budget.conta
           FROM projeto p
             JOIN ( SELECT bd.projeto_id AS id,
                    bd.conta_financeira AS conta,
                    0.00 AS valor_despesa,
                    sum(bd.valor) AS valor_budget
                   FROM budget bd GROUP BY bd.projeto_id,conta
                  ) budget ON p.id = budget.id
             JOIN ( SELECT tcf.descricao
                   FROM table_conta_financeira tcf
                  ) cta ON budget.conta::text = cta.descricao::text)) b
GROUP BY numero, conta
ORDER BY numero