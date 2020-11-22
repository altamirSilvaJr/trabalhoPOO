import java.util.*;

public class Main {
    public static void main(String[] args){
        int op = 0;
        Scanner sc = new Scanner(System.in);
        Caixa caixa = new Caixa();
        List<Produto> produtos = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
        List<Fornecedor> fornecedores = new ArrayList<>();
        int controleVenda = 0;

        do {
            System.out.printf("1 - Adicionar venda \n" +
                    "2 - Retirar venda \n" +
                    "3 - Relatório de vendas" +
                    "4 - Cadastrar Fornecedor \n" +
                    "5 - Cadastrar Produto \n" +
                    "0 - Sair \n" +
                    "Digite a opção desejada: ");
            op = sc.nextInt();

            switch (op){
                case 1:
                    int opVenda = 1;
                    Calendar cal = new GregorianCalendar();
                    Venda venda = new Venda();
                    venda.setDataVenda(cal.getTime());
                    while (opVenda != 0) {
                        System.out.println("Digite o código do produto que deseja incluir:");
                        int vendaCodProd = sc.nextInt();
                        boolean encontrouProduto = false;
                        for(Produto prod : produtos){
                            if(prod.getCodProguto() == vendaCodProd){
                                venda.setProdutoVenda(prod);
                                encontrouProduto = true;
                                break;
                            }
                        }
                        if(!encontrouProduto){
                            System.out.println("Não foi encontrado produto com o código especificado");
                        }
                        System.out.printf("Deseja adicionar mais produtos?\n 1 - Sim \n 0 - Não");
                    }
                    System.out.printf("Digite o desconto em porcentagem que deseja para a venda(apenas número inteiro): ");
                    venda.setDescontoVenda(sc.nextInt());
                    int opConcluir = 1;
                    while(opConcluir !=1 && opConcluir != 3) {
                        System.out.print("1 - Concluir venda \n 2 - Retirar algum produto da lsita? \n 3 - Cancelar venda(todo o processo de venda será perdido) \n" +
                        "Escolha a opção desejada: ");
                        opConcluir = sc.nextInt();
                        if(opConcluir == 2){
                            System.out.printf("Digite o código do produto que deseja retirar da lista: ");
                            int cancelaProduto = sc.nextInt();
                            boolean encontrouProdutoCancela = false;
                            for(Produto prod : produtos){
                                if(prod.getCodProguto() == cancelaProduto){
                                    venda.retirarProduto(prod);
                                    encontrouProdutoCancela = true;
                                    break;
                                }
                            }
                        }else if(opConcluir == 3){
                            venda = new Venda();
                        }else if(opConcluir == 1){
                            venda.valorTotal();
                            System.out.println("Valor total da compra: " + venda.getPrecoTotalVenda());
                            controleVenda++;
                            venda.setCodigoVenda(controleVenda);
                            vendas.add(venda);
                            caixa.adicionarValor(venda.getPrecoTotalVenda());
                        }else{
                            System.out.println("você digitou uma opção inválida");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Código | Data da Venda  | Desconto | Valor total;");
                    for(Venda vend : vendas){
                        System.out.println(vend);
                    }
                    System.out.printf("Digite o código da venda que deseja cancelar: ");
                    int cancelaVenda = sc.nextInt();

                    for(Venda vend : vendas){
                        if(vend.getCodigoVenda() == cancelaVenda){
                            vendas.remove(vend);
                            caixa.retirarValor(vend.getPrecoTotalVenda());
                            break;
                        }
                    }
                    break;

                case 3:
                    System.out.printf("Total de dia no caixa: " + caixa.getValorCaixa());
                    System.out.println("Código | Data da Venda  | Desconto | Valor total;");
                    for(Venda vend : vendas){
                        System.out.println(vend);
                    }
                    break;
                case 4:
                    Fornecedor fornecedor = new Fornecedor();
                    System.out.printf("Digite o nome do fornecedor: ");
                    fornecedor.setNomeFornecedor(sc.nextLine());
                    System.out.printf("Digite o CNPJ do fornecedor: ");
                    fornecedor.setCnpjFornecedor(sc.nextLine());
                    System.out.printf("Digite o telefone do fornecedor: ");
                    fornecedor.setTelefoneFornecedor(sc.nextInt());
                    System.out.printf("Digite o endereço do fornecedor: ");
                    fornecedor.setEnderecoFornecedor(sc.nextLine());
                    System.out.printf("Fornecedor cadastrado com suceso!");
                    break;
                case 5:
                    Produto produto = new Produto();
                    System.out.printf("Digite o código do produto: ");
                    produto.setCodProguto(sc.nextInt());
                    System.out.printf("Digite o nome do produto: ");
                    produto.setNomeProduto(sc.nextLine());
                    System.out.printf("Digite o valor de venda do produto: ");
                    produto.setValorVendaProduto(sc.nextDouble());
                    System.out.printf("Digite o valor de compra do produto: ");
                    produto.setValorCompraProduto(sc.nextDouble());
                    System.out.printf("Digite a descrição do produto: ");
                    produto.setDescricaoProduto(sc.nextLine());
                    System.out.printf("Digite o peso do produto: ");
                    produto.setPesoProduto(sc.nextDouble());
                    System.out.printf("Digite a quantidade em estoque do produto: ");
                    produto.setQtdProduto(sc.nextInt());
                    produtos.add(produto);
                    System.out.println("Produto cadastrando com sucesso");
                    break;
                default:
                    System.out.println("Você digitou uma opção inválida!");
            }
        }while  (op != 0) ;
    }
}
