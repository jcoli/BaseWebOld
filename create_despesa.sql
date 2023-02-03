DROP TABLE despesa;

CREATE TABLE despesa
(
  cd_conta character varying(60),
  conta_financeira character varying(60),
  conta_gerencial character varying(60),
  dt_vencimento timestamp without time zone,
  gerenc_dados character varying(60),
  gerenc_filter character varying(60),
  historico character varying(100),
  lanc_fin integer,
  mes character varying(30),
  percentual_rate real,
  previsto real,
  realizado real,
  projeto_id integer,
  id integer NOT NULL DEFAULT nextval('hibernate_sequence'::regclass),
  CONSTRAINT despesa_pkey PRIMARY KEY (id),
  CONSTRAINT fk_kovck19u04nm42sbra4t2vpe0 FOREIGN KEY (projeto_id)
      REFERENCES projeto (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE despesa
  OWNER TO saibr;
