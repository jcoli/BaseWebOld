SELECT p.id, budget.soma, budget.tipomaterial, despesa.valor, despesa.conta_financeira

 FROM projeto p

JOIN ( SELECT bd.projeto_id,
            bd.tipomaterial,
            sum(bd.valor) AS soma
           FROM budget bd
          GROUP BY bd.projeto_id, bd.tipomaterial)
           budget ON p.id = budget.projeto_id 
JOIN ( SELECT dp.projeto_id,
            dp.conta_financeira,
            sum(dp.realizado + dp.previsto) AS valor
           FROM despesa dp
          GROUP BY dp.projeto_id, dp.conta_financeira) despesa ON p.id = despesa.projeto_id  HAVING(despesa.conta_financeira) 




           