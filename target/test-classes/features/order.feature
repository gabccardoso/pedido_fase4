# language: pt

Funcionalidade: Order

  Cenario: Registrar pedido
    Quando registrar um novo pedido
    Entao o pedido é registrado com sucesso
    E é apresentado com sucesso

  Cenario: Buscar pedido
    Dado que já existe um pedido
    Quando buscar esse pedido
    Entao o pedido retorna com sucesso

  Cenario: Adicionar itens no pedido
    Dado que já existe um pedido
    E existe um produto com aquele ID
    Quando adicionar itens no pedido
    Entao o pedido deve atualizar com sucesso