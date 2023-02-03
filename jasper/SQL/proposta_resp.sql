SELECT p.id, p.ativa, p.dataentrega, p.dataportal, p.dataresponsavel, p.datastatus, 
       p.descricao, p.descricaocurta, p.numero, p.pac, p.statusatual, p.tipo, p.versao, 
       p.cliente_id, p.planta_id, p.responsavel, p.finalizacao, p.motivoperda, 
       p.contatocompras_id, p.contatoengenharia_id, p.contatosolicitante_id, 
       p.valorproposta, p.descricaodeclinada, p.numerocliente,
       u.codigo, u.area, u.ativo, u.celular, u.email, u.emailenviado, u.funcao, u.idioma, 
       u.login, u.nome, u.projetosativos, u.propostasativas, u.ratehour, u.ratehourest, 
       u.senha, u.idtipo
FROM   public.proposta p INNER JOIN public.user u ON p.responsavel = u.codigo
WHERE p.ativa = true
ORDER BY u.nome