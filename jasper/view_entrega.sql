SELECT e.datacadastro as datacadastro,
	e.dataentregacliente as dataentregacliente,
	e.dataentregafornecedor as dataentregafornecedor,
	e.dataentregastatus as dataentregastus,
	e.descricao as descricao,
	e.descricaocurta as descricaocurta,
	e.frete as frete,
	e.fretecompra as fretecompra,
	e.lead as lead,
	e.notafiscal as notafiscal,
	e.numero as numero,
	e.observacao as observacao,
	e.qtde as qtde,
	e.qtdeentregue as qtdeentregue,
	e.status as status, 
	e.statusfornecedor as statusfornecedor,
	e.tipo as tipo,
	e.unidade as unidade,
	e.valor as valor,
	e.valorcompra as valorcompra,
	e.valorcompratotal as valorcompratotal,
	e.valorunitario as valorunitario,
	au.numero as autornumero,
	cl.nome as clientenome,
	fo.nome as fornecedornome, 
	pe.numero, 
        pa.numero as pedidoabertonumero,
        pl.nome as plantanome,
        re.numero as requisicaonumero,
        e.origem as origem,
        e.numerocliente as numerocliente, 
        e.origemcompra as origemcompra,
        e.compraorigem as compraorigem,
         projeto_id
  FROM entrega e
  JOIN autorentrega au on e.autorentrega_id = au.id
  JOIN cliente cl on e.cliente_id = cl.id
  JOIN fornecedor fo on e.fornecedor_id = fo.id
  JOIN pedido pe on e.pedido_id = pe.id
  JOIN pedido_aberto pa on e.pedidoaberto_id = pa.id
  JOIN planta pl on e.planta_id = pl.id
  JOIN requisicao re on e.requisicao_id = re.id
  JOIN projeto pr on e.projeto_id - pr.id;
