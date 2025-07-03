Sistema de Caixa Registradora
Este é um sistema de caixa registradora simples, desenvolvido em Java com interface gráfica Swing, projetado para simular operações básicas de venda e gerenciamento de produtos em um ambiente de varejo.

Descrição do Projeto
O projeto "Caixa Registradora" oferece uma solução intuitiva para gerenciar vendas e estoque de produtos. Ele permite que o usuário interaja com um catálogo de produtos, adicione itens a uma venda, finalize transações e visualize o troco, além de gerar um registro detalhado da compra em formato de nota fiscal TXT.

Funcionalidades
O sistema de Caixa Registradora inclui as seguintes funcionalidades principais:

Gerenciamento de Produtos:

Adicionar Produtos: Inserir novos produtos no catálogo.

Listar Produtos: Visualizar todos os produtos disponíveis no catálogo.

Excluir Produto: Remover produtos do catálogo.

Processamento de Vendas:

Adicionar Item ao Carrinho: Selecionar produtos do catálogo e adicioná-los à venda atual.

Finalizar Venda: Concluir a transação, calculando o total da compra.

Cálculo de Troco: Determinar o troco a ser dado ao cliente com base no valor recebido.

Geração de Nota Fiscal:

Recibo em TXT: Gerar uma nota fiscal detalhada da compra em um arquivo de texto (.txt), incluindo todos os itens, o total da venda e o troco.

Exibição no Terminal: A nota fiscal completa também é exibida no terminal para feedback imediato.

Como Executar
Para executar este projeto, siga os passos abaixo:

Pré-requisitos:

Certifique-se de ter o JDK (Java Development Kit) instalado (versão 8 ou superior, idealmente a versão que você está usando, como OpenJDK 24).

Um ambiente de desenvolvimento integrado (IDE) como IntelliJ IDEA é recomendado.

Clonar o Repositório:

git clone https://github.com/Biab14/Caixa-Registradora.git
cd Caixa-Registradora

Se o seu repositório principal contém o Caixa-Registradora como um submódulo, você precisará inicializá-lo após o clone:

git submodule update --init --recursive

Abrir no IntelliJ IDEA:

Abra o projeto no IntelliJ IDEA (File > Open > selecione a pasta Caixa-Registradora).

Certifique-se de que o Project SDK está configurado corretamente (File > Project Structure > Project).

Configurar Módulos e Dependências:

Se o seu projeto tiver múltiplos módulos (ex: NovaVenda, Cadastro-), verifique em File > Project Structure > Modules se as pastas src estão marcadas como "Sources Root" e se as dependências entre os módulos estão configuradas (ex: NovaVenda dependendo de Cadastro-).

Compilar e Executar:

Vá para a classe Main.java (geralmente em src/Main.java ou NovaVenda/src/Main.java).

Clique com o botão direito na classe Main e selecione "Run 'Main.main()'" ou use a configuração de execução criada.

Ao finalizar uma venda, uma pasta notas_fiscais será criada no diretório raiz do projeto, contendo os arquivos .txt das notas fiscais geradas.

Tecnologias Utilizadas
Java

Swing (para a interface gráfica)