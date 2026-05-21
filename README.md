> BANCO DIGITAL:

Sobre a atividade:

- Vale 10 e tem peso 1;
- Em dupla;
- Entrega dia 26/05 – Apresentação dia 27/05;
- Utilizar validação (@Valid) criar no mínimo 8 atributos de classe anotados com alguma validação; (utilizar um mínimo de 5 validações diferentes)
- Utilizar em no mínimo 6 atributos de classe o @Collumn alterando as características da coluna no banco de dados;
- Utilizar no mínimo 1 padrão de projeto: a) Observer: Notificar outros objetos; b) Factory Method: Criar fábrica de alguma classe; c) Singleton: Garantir única instância de uma classe importante; d) Comparator: Ordenar os dados de uma tabela por x, y, etc; e) Strategy: Como visto em sala
ATIVIDADE 08: SISTEMA DE BANCO DIGITAL Cliente, Gerente, Cartão e Conta. Obs: CRUD de Cliente, CRUD de Gerente.

- OBS: abstração, associação, tratamento de exceção e encapsulamento.

> Entidades:

- Pessoa; (id, nome, cpf, n_telefone, data_nasc)
	Cliente (renda), Gerente (salario)
	
- Conta; (id, saldo, fatura)
	ContaCorrente (cheque_especial), ContaPoupanca (rendimento)
	
- Cartão; (id, numero, cvc, validade)
	CartaoCredito (limite), CartaoDebito ()

> Relacionamentos:

- Um Cliente pode possuir várias contas (OneToMany)
- Várias contas podem pertencer a um cliente (ManyToOne)
- Varios Cartões pertencem a uma conta (ManyToOne)
- Uma Conta possui vários cartões (OneToMany)

  
> Fluxo:

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


> Padrões de Projeto:

- Strategy (Interface: StrategyTaxa. Método Abstrato: taxa(). Classes Concretas: TaxaContaCorrente, TaxaContaPoupanca);
	[Strategy para taxas em transações que envolvem as contas]
		
- Factory Method (Classe: Factory. Método: criar: if = POUPANCA, return new Poupanca);
	[Factory para criar contas]

> Fluxo do Strategy + Simple Factory:

1- O ClienteService recebe o cliente (instância da model) e verifica na Factory: "Com base na renda, qual classe eu devo usar?"
2- A Factory executa o if (renda > 2000), instancia o objeto correto (new LimiteAltoStrategy()) e retorna através da interface.
3- O ClienteService recebe esse objeto e executa o Strategy: strategy.calcular(...)

[Se quiser adicionar uma nova regra - você não precisará alterar o 'Service', mas sim criar uma nova 'strategy' e adicioná-la na 'factory']

> Passo a Passo

- Criar Model (Atributos, Validação, Relacionamentos);
- Criar Repository (Métodos do Spring Data JPA. É utilizado na Service);
- Criar Service (Usar 'Try...Catch'. @Autowired. Usa Repository);
- Criar Controller (CRUD - Gerente, Cliente. Definir Rotas: @GetMapping('nome_rota'). Retornar HTML. Chama o Service)
- Criar Views/Thymeleaf (Telas: Homepage [Navbar: cliente, gerente] | Cliente [CRUD] | Gerente [CRUD])
