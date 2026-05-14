> BANCO DIGITAL:

Sobre a atividade:
1. Vale 10 e tem peso 1;
2. Em dupla;
3. Entrega dia 26/05 – Apresentação dia 27/05;
4. Utilizar validação (@Valid) criar no mínimo 8 atributos de classe anotados com alguma 
validação; (utilizar um mínimo de 5 validações diferentes)
5. Utilizar em no mínimo 6 atributos de classe o @Collumn alterando as características da 
coluna no banco de dados;
6. Utilizar no mínimo 1 padrão de projeto:
a) Observer: Notificar outros objetos;
b) Factory Method: Criar fábrica de alguma classe;
c) Singleton: Garantir única instância de uma classe importante;
d) Comparator: Ordenar os dados de uma tabela por x, y, etc;
e) Strategy: Como visto em sala



> ATIVIDADE 08: SISTEMA DE BANCO DIGITAL
Cliente, Gerente, Cartão e Conta.
Obs: CRUD de Cliente, CRUD de Gerente.

Observações: abstração, associação, tratamento de exceção e encapsulamento.


Entidades:
	- Pessoa; (id, nome, cpf, n_telefone, data_nasc)
		Cliente (renda), Gerente (salario)
		
	- Conta; (id, saldo, fatura)
		ContaCorrente (cheque_especial), ContaPoupanca (rendimento)
		
	- Cartão; (id, numero, cvc, validade)
		CartaoCredito (limite), CartaoDebito ()


Relacionamentos:
	- Um Cliente pode possuir várias contas ()
	- Várias contas podem pertencer a um cliente ()


Fluxo:
	- View (Thymeleaf - acessa o controller pela 'rota definida')
		[Telas: homepage, cliente: form/list, gerente: form/list]
		
	- Controller (API - chama o service)
		[Métodos: get, post, delete, put]
		
	- Service (Regras de Negócio - chama o repository)
		[Usa Exceções, Aplica Regras de Negócio]
		
	- Repository (JPA - realizar operações do bd]
		[Métodos: findById, all, save, delete, etc.]
		
	- Entity/Model (ORM - mapeia classes em tabelas)
		[Uso: restrições, relacionamentos, hierarquias]


Padrões de Projeto:
	- Strategy (Interface: StrategyTaxa. Método Abstrato: taxa(). Classes Concretas: TaxaContaCorrente, TaxaContaPoupanca);
		[]
			
	- Factory Method (Classe: Factory. Método: criar: if = POUPANCA, return new Poupanca);
		[]
