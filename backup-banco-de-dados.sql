--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.22
-- Dumped by pg_dump version 9.5.22

-- Started on 2021-08-24 23:43:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2219 (class 1262 OID 32933)
-- Name: sistema-escritorio-advocacia; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "sistema-escritorio-advocacia" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE "sistema-escritorio-advocacia" OWNER TO postgres;

\connect -reuse-previous=on "dbname='sistema-escritorio-advocacia'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2222 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 194 (class 1259 OID 41850)
-- Name: audiencia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.audiencia (
    id bigint NOT NULL,
    data_da_audiencia date NOT NULL,
    endereco character varying(255) NOT NULL,
    flag_precatoria boolean NOT NULL,
    horario character varying(255) NOT NULL,
    observacao character varying(255) NOT NULL,
    vara character varying(255) NOT NULL,
    processo_id bigint,
    numero_processo_gerado character varying(255),
    testemunhas character varying(255)
);


ALTER TABLE public.audiencia OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 33106)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    cliente_id bigint NOT NULL,
    cnpj character varying(255),
    cpf character varying(255),
    email character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    endereco_id bigint
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 33114)
-- Name: cliente_contatos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente_contatos (
    cliente_cliente_id bigint NOT NULL,
    contatos_contato_id bigint NOT NULL
);


ALTER TABLE public.cliente_contatos OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 33117)
-- Name: contato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contato (
    contato_id bigint NOT NULL,
    numero character varying(255) NOT NULL,
    tipo character varying(255) NOT NULL
);


ALTER TABLE public.contato OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 33125)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endereco (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 33030)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 41858)
-- Name: pericia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pericia (
    id bigint NOT NULL,
    data_da_pericia date NOT NULL,
    endereco character varying(255) NOT NULL,
    horario character varying(255) NOT NULL,
    nome_perito character varying(255) NOT NULL,
    telefone character varying(255) NOT NULL,
    processo_id bigint
);


ALTER TABLE public.pericia OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 33138)
-- Name: processo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.processo (
    id bigint NOT NULL,
    numero character varying(255) NOT NULL,
    observacao character varying(255) NOT NULL,
    situacao_id bigint
);


ALTER TABLE public.processo OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 33146)
-- Name: processo_cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.processo_cliente (
    processo_id bigint NOT NULL,
    cliente_id bigint NOT NULL
);


ALTER TABLE public.processo_cliente OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 33149)
-- Name: processo_reu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.processo_reu (
    processo_id bigint NOT NULL,
    reu_id bigint NOT NULL
);


ALTER TABLE public.processo_reu OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 33152)
-- Name: reu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reu (
    id bigint NOT NULL,
    cnpj character varying(255),
    cpf character varying(255),
    nome character varying(255) NOT NULL
);


ALTER TABLE public.reu OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 41333)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 33004)
-- Name: situacao_processo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.situacao_processo (
    id bigint NOT NULL,
    situacao character varying(255)
);


ALTER TABLE public.situacao_processo OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 41300)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    login character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    senha character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 41341)
-- Name: usuario_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_role (
    usuario_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.usuario_role OWNER TO postgres;

--
-- TOC entry 2212 (class 0 OID 41850)
-- Dependencies: 194
-- Data for Name: audiencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.audiencia (id, data_da_audiencia, endereco, flag_precatoria, horario, observacao, vara, processo_id, numero_processo_gerado, testemunhas) VALUES (1, '2021-06-21', 'Rua Teste', false, '16:00', 'Teste', '16', 1, NULL, NULL);
INSERT INTO public.audiencia (id, data_da_audiencia, endereco, flag_precatoria, horario, observacao, vara, processo_id, numero_processo_gerado, testemunhas) VALUES (2, '2021-09-21', 'Rua Teste 2', true, '16:00', 'Teste 2', '16 ', 1, '322443243342234423', 'Minha mae');


--
-- TOC entry 2201 (class 0 OID 33106)
-- Dependencies: 183
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (20, NULL, '17468574899', 'ricardo.oliveira@hotmail.com', 'Ricardo da Silva Oliveira', 27);
INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (8, NULL, '06637971529', 'igor.ptz@hotmail.com', 'Igor dos Santos Pereira', 28);
INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (55, NULL, '3432234324224', 'teste3@hotmail.com', 'Teste3', 56);
INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (67, NULL, '324232332432', 'teste@gmail.com', 'Teste5', 74);
INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (61, NULL, '34455345345', 'teste4@gmail.com', 'Teste4', 75);
INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (46, NULL, '2444353344543', 'clodoaldo.valerio@hotmail.com', 'Clodoaldo Valerio Pereira', 82);
INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (98, NULL, '06637971549', 'igor.pereira@hotmail.com', 'Igor Oliveira Pereira', 99);
INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (104, NULL, '17468544523', 'beltrano@hotmail.com', 'Beltrano da Silva Pereira', 105);
INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (110, NULL, '4558992121', 'silvana.pereira@hotmail.com', 'Silvana da Silva Pereira', 116);
INSERT INTO public.cliente (cliente_id, cnpj, cpf, email, nome, endereco_id) VALUES (40, NULL, '17468599593', 'reginaldo.farias@hotmail.com', 'Reginaldo da Silva Farias', 146);


--
-- TOC entry 2202 (class 0 OID 33114)
-- Dependencies: 184
-- Data for Name: cliente_contatos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (20, 22);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (20, 23);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (20, 24);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (20, 25);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (8, 10);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (8, 11);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (8, 12);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (8, 13);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (55, 57);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (55, 58);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (55, 59);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (55, 60);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (67, 69);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (67, 70);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (67, 71);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (67, 72);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (61, 63);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (61, 64);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (61, 65);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (61, 66);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (46, 48);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (46, 49);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (46, 50);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (46, 51);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (98, 100);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (98, 101);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (98, 102);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (98, 103);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (104, 106);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (104, 107);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (104, 108);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (104, 109);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (110, 112);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (110, 113);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (110, 114);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (110, 115);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (40, 142);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (40, 143);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (40, 144);
INSERT INTO public.cliente_contatos (cliente_cliente_id, contatos_contato_id) VALUES (40, 145);


--
-- TOC entry 2203 (class 0 OID 33117)
-- Dependencies: 185
-- Data for Name: contato; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.contato (contato_id, numero, tipo) VALUES (10, '71996996229', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (11, '7133099342', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (12, '71991492129', 'Comercial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (22, '11996996229', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (23, '1133099332', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (24, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (25, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (13, '71992492239', 'Comercial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (48, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (49, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (50, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (51, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (42, '71991489981', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (43, '71992422121', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (44, '7133492828', 'Comercial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (45, '7133924242', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (57, '71996996229', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (58, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (59, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (60, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (63, '71996996224', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (64, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (65, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (66, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (69, '3423343223322', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (70, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (71, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (72, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (100, '71996996229', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (101, '7133099342', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (102, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (103, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (106, '71991942929', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (107, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (108, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (109, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (113, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (114, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (115, '', '');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (112, '719924242', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (117, '71991489984', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (118, '71992422121', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (119, '7133492828', 'Comercial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (120, '7133924242', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (122, '71991489984', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (123, '71992422121', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (124, '7133492828', 'Comercial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (125, '7133924242', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (127, '71991489984', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (128, '71992422121', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (129, '7133492828', 'Comercial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (130, '7133924242', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (132, '71991489984', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (133, '71992422121', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (134, '7133492828', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (135, '7133924242', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (137, '71991489984', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (138, '71992422121', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (139, '7133492828', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (140, '7133924242', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (142, '71991489984', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (143, '71992422121', 'Celular');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (144, '7133492828', 'Residencial');
INSERT INTO public.contato (contato_id, numero, tipo) VALUES (145, '7133924242', 'Residencial');


--
-- TOC entry 2204 (class 0 OID 33125)
-- Dependencies: 186
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.endereco (id, descricao) VALUES (9, 'Rua Rosa Parks 107');
INSERT INTO public.endereco (id, descricao) VALUES (21, 'Rua do Corre - 107 - Vila Madalena');
INSERT INTO public.endereco (id, descricao) VALUES (26, 'Rua do Corre - 107 - Vila Madalena');
INSERT INTO public.endereco (id, descricao) VALUES (27, 'Rua do Corre - 107 - Vila Madalena');
INSERT INTO public.endereco (id, descricao) VALUES (28, 'Rua Rosa Parks 107');
INSERT INTO public.endereco (id, descricao) VALUES (41, '');
INSERT INTO public.endereco (id, descricao) VALUES (47, '');
INSERT INTO public.endereco (id, descricao) VALUES (52, 'Rua Teste');
INSERT INTO public.endereco (id, descricao) VALUES (53, 'Rua do Teste');
INSERT INTO public.endereco (id, descricao) VALUES (54, 'Rua do Teste');
INSERT INTO public.endereco (id, descricao) VALUES (56, 'er23234234324');
INSERT INTO public.endereco (id, descricao) VALUES (62, '3242342243343342');
INSERT INTO public.endereco (id, descricao) VALUES (68, '323243232343234');
INSERT INTO public.endereco (id, descricao) VALUES (73, '323243232343234');
INSERT INTO public.endereco (id, descricao) VALUES (74, '323243232343234');
INSERT INTO public.endereco (id, descricao) VALUES (75, '3242342243343342');
INSERT INTO public.endereco (id, descricao) VALUES (82, 'Rua do Teste');
INSERT INTO public.endereco (id, descricao) VALUES (99, 'Rua Rosa parks 214');
INSERT INTO public.endereco (id, descricao) VALUES (105, 'Rua do Beltrano n 110');
INSERT INTO public.endereco (id, descricao) VALUES (111, '');
INSERT INTO public.endereco (id, descricao) VALUES (116, 'Rua da Silvana');
INSERT INTO public.endereco (id, descricao) VALUES (121, 'Rua do Reginaldo nº 100');
INSERT INTO public.endereco (id, descricao) VALUES (126, 'Rua do Reginaldo nº 100');
INSERT INTO public.endereco (id, descricao) VALUES (131, 'Rua do Reginaldo nº 100');
INSERT INTO public.endereco (id, descricao) VALUES (136, 'Rua do Reginaldo nº 100');
INSERT INTO public.endereco (id, descricao) VALUES (141, 'Rua do Reginaldo nº 100');
INSERT INTO public.endereco (id, descricao) VALUES (146, 'Rua do Reginaldo nº 100');


--
-- TOC entry 2223 (class 0 OID 0)
-- Dependencies: 182
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 147, true);


--
-- TOC entry 2213 (class 0 OID 41858)
-- Dependencies: 195
-- Data for Name: pericia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pericia (id, data_da_pericia, endereco, horario, nome_perito, telefone, processo_id) VALUES (90, '2021-10-21', 'Forum Ruy Barbosa', '14:00', 'Dr Roberto Farias', '7133094922', 1);
INSERT INTO public.pericia (id, data_da_pericia, endereco, horario, nome_perito, telefone, processo_id) VALUES (1, '2021-10-21', 'Rua do Hospital', '15:00', 'Dr Jose Barros', '71996996229', 1);


--
-- TOC entry 2205 (class 0 OID 33138)
-- Dependencies: 187
-- Data for Name: processo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.processo (id, numero, observacao, situacao_id) VALUES (80, '5345345435345', 'Teste', 1);
INSERT INTO public.processo (id, numero, observacao, situacao_id) VALUES (83, '5435231323131', 'Teste Teste', 4);
INSERT INTO public.processo (id, numero, observacao, situacao_id) VALUES (85, '5465465465465465464455445544', 'qewerwrwerwewewer', 1);
INSERT INTO public.processo (id, numero, observacao, situacao_id) VALUES (76, '12345678910', 'Teste', 1);
INSERT INTO public.processo (id, numero, observacao, situacao_id) VALUES (1, '123456789', 'Teste', 2);
INSERT INTO public.processo (id, numero, observacao, situacao_id) VALUES (147, '240820211', 'TESTE 24-08-21', 1);


--
-- TOC entry 2206 (class 0 OID 33146)
-- Dependencies: 188
-- Data for Name: processo_cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.processo_cliente (processo_id, cliente_id) VALUES (80, 20);
INSERT INTO public.processo_cliente (processo_id, cliente_id) VALUES (83, 20);
INSERT INTO public.processo_cliente (processo_id, cliente_id) VALUES (83, 46);
INSERT INTO public.processo_cliente (processo_id, cliente_id) VALUES (85, 8);
INSERT INTO public.processo_cliente (processo_id, cliente_id) VALUES (76, 8);
INSERT INTO public.processo_cliente (processo_id, cliente_id) VALUES (1, 20);
INSERT INTO public.processo_cliente (processo_id, cliente_id) VALUES (1, 8);
INSERT INTO public.processo_cliente (processo_id, cliente_id) VALUES (147, 8);
INSERT INTO public.processo_cliente (processo_id, cliente_id) VALUES (147, 40);


--
-- TOC entry 2207 (class 0 OID 33149)
-- Dependencies: 189
-- Data for Name: processo_reu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (80, 2);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (80, 5);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (83, 1);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (83, 5);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (85, 2);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (85, 3);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (76, 1);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (76, 2);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (1, 1);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (1, 2);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (147, 4);
INSERT INTO public.processo_reu (processo_id, reu_id) VALUES (147, 5);


--
-- TOC entry 2208 (class 0 OID 33152)
-- Dependencies: 190
-- Data for Name: reu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.reu (id, cnpj, cpf, nome) VALUES (1, '123456789', NULL, 'BRADESCO');
INSERT INTO public.reu (id, cnpj, cpf, nome) VALUES (2, '987654321', NULL, 'SANTANDER');
INSERT INTO public.reu (id, cnpj, cpf, nome) VALUES (3, '4355354565', NULL, 'CAIXA');
INSERT INTO public.reu (id, cnpj, cpf, nome) VALUES (4, '4543533553', NULL, 'ITAÚ');
INSERT INTO public.reu (id, cnpj, cpf, nome) VALUES (5, '34435535334', NULL, 'BANCO DO BRASIL');


--
-- TOC entry 2210 (class 0 OID 41333)
-- Dependencies: 192
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id, descricao, nome) VALUES (1, 'Administrador', 'ROLE_ADMIN');
INSERT INTO public.role (id, descricao, nome) VALUES (2, 'Advogado', 'ROLE_ADVOGADO');


--
-- TOC entry 2199 (class 0 OID 33004)
-- Dependencies: 181
-- Data for Name: situacao_processo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.situacao_processo (id, situacao) VALUES (1, 'Conhecimento');
INSERT INTO public.situacao_processo (id, situacao) VALUES (3, 'Concluído');
INSERT INTO public.situacao_processo (id, situacao) VALUES (4, 'Apensado');
INSERT INTO public.situacao_processo (id, situacao) VALUES (2, 'Execução');


--
-- TOC entry 2209 (class 0 OID 41300)
-- Dependencies: 191
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (id, login, nome, senha) VALUES (30, 'igor.ptz@hotmail.com', 'Igor', '$2a$10$F8IiFxEziQRqLb7BND23Hu.nHmkdoCPMaRHO.NvIskLjjE2rM2W9.');
INSERT INTO public.usuario (id, login, nome, senha) VALUES (33, 'leanderson@hotmail.com', 'Leanderson', '$2a$10$ZosnzPLNMUqyUzBnQpcIU.5bndg7z0pHkW9rFvgZRhvGar1lfeA2W');
INSERT INTO public.usuario (id, login, nome, senha) VALUES (39, 'carlos@hotmail.com', 'Carlos', '$2a$10$I7hzOciEdehJv8J.QHE65OzlM31ddQMMxI0x7iGqGo9JlR2iCVB9C');
INSERT INTO public.usuario (id, login, nome, senha) VALUES (1, 'admin', 'Admin', '$2a$10$ZfKtDqi4ENG.SXO44kmoFu0WylbHsSrBEkfN6NbJmNpzhOUJ49JZ2');


--
-- TOC entry 2211 (class 0 OID 41341)
-- Dependencies: 193
-- Data for Name: usuario_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario_role (usuario_id, role_id) VALUES (1, 1);
INSERT INTO public.usuario_role (usuario_id, role_id) VALUES (30, 2);
INSERT INTO public.usuario_role (usuario_id, role_id) VALUES (33, 1);
INSERT INTO public.usuario_role (usuario_id, role_id) VALUES (39, 2);


--
-- TOC entry 2070 (class 2606 OID 41857)
-- Name: audiencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.audiencia
    ADD CONSTRAINT audiencia_pkey PRIMARY KEY (id);


--
-- TOC entry 2044 (class 2606 OID 33113)
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cliente_id);


--
-- TOC entry 2052 (class 2606 OID 33124)
-- Name: contato_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contato
    ADD CONSTRAINT contato_pkey PRIMARY KEY (contato_id);


--
-- TOC entry 2054 (class 2606 OID 33129)
-- Name: endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 2056 (class 2606 OID 41726)
-- Name: numero_processo_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo
    ADD CONSTRAINT numero_processo_unique UNIQUE (numero);


--
-- TOC entry 2072 (class 2606 OID 41865)
-- Name: pericia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pericia
    ADD CONSTRAINT pericia_pkey PRIMARY KEY (id);


--
-- TOC entry 2058 (class 2606 OID 33145)
-- Name: processo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo
    ADD CONSTRAINT processo_pkey PRIMARY KEY (id);


--
-- TOC entry 2060 (class 2606 OID 33159)
-- Name: reu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reu
    ADD CONSTRAINT reu_pkey PRIMARY KEY (id);


--
-- TOC entry 2066 (class 2606 OID 41340)
-- Name: role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 2042 (class 2606 OID 33008)
-- Name: situacao_processo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.situacao_processo
    ADD CONSTRAINT situacao_processo_pkey PRIMARY KEY (id);


--
-- TOC entry 2046 (class 2606 OID 33172)
-- Name: uk_2vf89ija5fea0souakqh3bg59; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT uk_2vf89ija5fea0souakqh3bg59 UNIQUE (cnpj);


--
-- TOC entry 2050 (class 2606 OID 33176)
-- Name: uk_4m4ewp4paovc7dutj542hk0t6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente_contatos
    ADD CONSTRAINT uk_4m4ewp4paovc7dutj542hk0t6 UNIQUE (contatos_contato_id);


--
-- TOC entry 2062 (class 2606 OID 41316)
-- Name: uk_pm3f4m4fqv89oeeeac4tbe2f4; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk_pm3f4m4fqv89oeeeac4tbe2f4 UNIQUE (login);


--
-- TOC entry 2048 (class 2606 OID 33174)
-- Name: uk_r1u8010d60num5vc8fp0q1j2a; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT uk_r1u8010d60num5vc8fp0q1j2a UNIQUE (cpf);


--
-- TOC entry 2068 (class 2606 OID 41374)
-- Name: unique_usuario_role; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_role
    ADD CONSTRAINT unique_usuario_role UNIQUE (role_id, usuario_id);


--
-- TOC entry 2064 (class 2606 OID 41307)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2073 (class 2606 OID 33186)
-- Name: fk64nr9yt889by5lufr1boo5i4s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk64nr9yt889by5lufr1boo5i4s FOREIGN KEY (endereco_id) REFERENCES public.endereco(id);


--
-- TOC entry 2075 (class 2606 OID 33196)
-- Name: fk6lf0rtckjocj43dae8spxtp7m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente_contatos
    ADD CONSTRAINT fk6lf0rtckjocj43dae8spxtp7m FOREIGN KEY (cliente_cliente_id) REFERENCES public.cliente(cliente_id);


--
-- TOC entry 2080 (class 2606 OID 33226)
-- Name: fk70mk8n0hmiv735h5eve82xglj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo_reu
    ADD CONSTRAINT fk70mk8n0hmiv735h5eve82xglj FOREIGN KEY (processo_id) REFERENCES public.processo(id);


--
-- TOC entry 2081 (class 2606 OID 41346)
-- Name: fke7gfguqsiox6p89xggm8g2twf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_role
    ADD CONSTRAINT fke7gfguqsiox6p89xggm8g2twf FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- TOC entry 2084 (class 2606 OID 41873)
-- Name: fkevpm7dgfqtxql6ccb67tweg54; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pericia
    ADD CONSTRAINT fkevpm7dgfqtxql6ccb67tweg54 FOREIGN KEY (processo_id) REFERENCES public.processo(id);


--
-- TOC entry 2077 (class 2606 OID 33211)
-- Name: fkfelwdhj7li7bn9iiq2yv40gkj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo_cliente
    ADD CONSTRAINT fkfelwdhj7li7bn9iiq2yv40gkj FOREIGN KEY (cliente_id) REFERENCES public.cliente(cliente_id);


--
-- TOC entry 2076 (class 2606 OID 33206)
-- Name: fkg2wj48jsxbf0ibivmskj3e47m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo
    ADD CONSTRAINT fkg2wj48jsxbf0ibivmskj3e47m FOREIGN KEY (situacao_id) REFERENCES public.situacao_processo(id);


--
-- TOC entry 2082 (class 2606 OID 41351)
-- Name: fkpc2qjts6sqq4hja9f6i3hf0ep; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_role
    ADD CONSTRAINT fkpc2qjts6sqq4hja9f6i3hf0ep FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- TOC entry 2078 (class 2606 OID 33216)
-- Name: fkpmdgeenfp0hsex0ne6k5rnffm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo_cliente
    ADD CONSTRAINT fkpmdgeenfp0hsex0ne6k5rnffm FOREIGN KEY (processo_id) REFERENCES public.processo(id);


--
-- TOC entry 2079 (class 2606 OID 33221)
-- Name: fkpv4xb1cndrsnq5swot7f7ckp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo_reu
    ADD CONSTRAINT fkpv4xb1cndrsnq5swot7f7ckp FOREIGN KEY (reu_id) REFERENCES public.reu(id);


--
-- TOC entry 2083 (class 2606 OID 41868)
-- Name: fkq8an2a8t0dnoqb47c2hbdbg4c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.audiencia
    ADD CONSTRAINT fkq8an2a8t0dnoqb47c2hbdbg4c FOREIGN KEY (processo_id) REFERENCES public.processo(id);


--
-- TOC entry 2074 (class 2606 OID 33191)
-- Name: fkrlinwrr33r9bwk881ic0mtygq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente_contatos
    ADD CONSTRAINT fkrlinwrr33r9bwk881ic0mtygq FOREIGN KEY (contatos_contato_id) REFERENCES public.contato(contato_id);


--
-- TOC entry 2221 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2021-08-24 23:43:04

--
-- PostgreSQL database dump complete
--

