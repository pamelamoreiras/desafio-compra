# desafio-compra
O objetivo é criar um endpoint para que possamos simular a compra de um produto, deve retornar uma lista de parcelas, acrescidas de juros com base na taxa SELIC de 1.15% ao mês (se possível consultar a taxa em tempo real), somente quando o número de parcelas for superior a 06 (seis) parcelas.
### Request da requisição
* Produto -> código, nome e valor.
* Condição de Pagamento -> valor de entrada e quantidade de parcelas.
* Exemplo:
{ "produto": { "codigo": 123, 
               "nome": "Nome do Produto",
               "valor": 9999.99 }, 
  "condicaoPagamento": { "valorEntrada": 9999.99, 
                         "qtdeParcelas": 999 } 
}
### Response da requisição
* Lista de parcelas -> número da parcela, valor e taxa de juros ao mês (se houver).
* Exemplo: [{ "numeroParcela": 1, 
              "valor": 9999.99, 
              "taxaJurosAoMes": 9999 }]
