SELECT id, datafinal, datainicio, descricao, descricaocurta, totalhoras, 
       cliente_id, planta_id, projeto_id, user, user_codigo, contahora, 
       valor, valorhora
  FROM timetracker where user_codigo=734 and (datainicio<='2014-09-08 20:00:00 ' and datafinal>='2014-09-08 07:00:00') and
			((datainicio>='2014-09-08 07:00:00' and datafinal<='2014-09-08 20:00:00')
		      OR (datainicio<='2014-09-08 07:00:00' and datafinal<='2014-09-08 20:00:00')
		      OR (datainicio>='2014-09-08 07:00:00' and datafinal>='2014-09-08 20:00:00')
		      OR (datainicio<='2014-09-08 07:00:00' and datafinal>='2014-09-08 20:00:00'))
  ;
