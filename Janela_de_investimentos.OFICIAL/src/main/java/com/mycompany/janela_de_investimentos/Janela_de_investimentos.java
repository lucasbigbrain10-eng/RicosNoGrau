package com.mycompany.janela_de_investimentos;

//usuarios:
//pedro senha:123
//jean senha:1234


//import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.text.NumberFormat;
import java.util.ArrayList;
//import static java.lang.String.format;
import java.util.Locale;
import javax.swing.JComboBox;
//import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.text.NumberFormatter;
import dao.LoginDAO;
//imports do banco de dados
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Janela_de_investimentos {

    public static void main(String[] args) {
        //colocando o negocio pra deixar mais bonito
        // Light, Dark, IntelliJ and Darcula themes,
        FlatLightLaf.setup();
        
        
        //criando o historico de investimentos
        ArrayList<String> historico = new ArrayList<>();

        
        //definindo a moeda como real 
        NumberFormat moedaformat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        moedaformat.setMaximumFractionDigits(2);
        
        
        //lista das ações de investimento
        String[] Acoes = {"Ouro", "Petróleo", "Bitcoin", "Ações Brasileiras", "Fundos Imobiliários", "ETFs", "Ações Americanas"};
        //criando a JComboBox que é a famosa caixa de seleção
        JComboBox listaAcoes = new JComboBox(Acoes);
        listaAcoes.setSelectedIndex(4);
        listaAcoes.setBounds(50, 200, 150, 30);
        
        
        // Cria um NumberFormatter com o formatador
        NumberFormatter formatter = new NumberFormatter(moedaformat);
        formatter.setAllowsInvalid(false); // Não permite caracteres inválidos
        formatter.setOverwriteMode(true); // Sobrescreve o texto em vez de inseri-lo

        // adicionar uma janela e Definir tamanho
        JFrame janela1 = new JFrame();
        janela1.setBounds(0, 0, 400, 300);
        //janela1.getContentPane().setBackground(Color.green);

        JFrame janela2 = new JFrame();
        janela2.setBounds(500, 0, 1200, 500);
        
        JFrame janela3 = new JFrame();
        janela3.setBounds(50, 500, 900, 500);
        
        JFrame janela4 = new JFrame();
        janela4.setBounds(1000, 500, 900, 500);
        
        
        //adicionar um botão e Definir tamanho
        JButton botaoLogar = new JButton("Login");
        botaoLogar.setBounds(50, 200, 75, 30);

        JButton botaoReset = new JButton("Apagar");
        botaoReset.setBounds(150, 200, 75, 30);
        
        JButton botaoAplicar = new JButton("Aplicar");
        botaoAplicar.setBounds(50, 400, 75, 30);
        
        JButton botaoCriar = new JButton("Criar Usuário");
        botaoCriar.setBounds(250, 200, 120, 30);

        //adicionar uma escrita na tela e Definir tamanho
        JLabel labelTitulo1 = new JLabel("Efetue o Login para entrar no hub de investimentos");
        labelTitulo1.setForeground(Color.black);
        labelTitulo1.setBounds(50, 20, 300, 30);
        
        JLabel labelTitulo2 = new JLabel("Seja bem vindo ao hub de investimentos: ");
        labelTitulo2.setForeground(Color.black);
        labelTitulo2.setBounds(50, 20, 300, 30);
        
        JLabel labelTitulo3 = new JLabel("Seja bem vindo ao hub de valorização dos ativos no mercado: ");
        labelTitulo3.setForeground(Color.black);
        labelTitulo3.setBounds(50, 20, 400, 30);
        
        //só pra eu lembrar como centraliza o texto: , JLabel.CENTER
        JLabel labelTitulo4 = new JLabel("Fique por dentro das noticias mais recentes ao redor do mundo: ");
        labelTitulo4.setForeground(Color.black);
        labelTitulo4.setBounds(50, 20, 400, 30);
        
        JLabel labelIdeposito = new JLabel("O gráfico mostra a oscilação do investimento.");
        labelIdeposito.setBounds(50, 200, 250, 200);
        
        JLabel labelIdeposito2 = new JLabel("Quando o valor sobe, você lucra; quando cai, ");
        labelIdeposito2.setBounds(50, 225, 250, 200);
        
        JLabel labelIdeposito3 = new JLabel("Perde proporcionalmente ao que foi aplicado.");
        labelIdeposito3.setBounds(50, 250, 250, 200);
        
        JLabel labelValor = new JLabel("Valor a ser investido/aplicado: ");
        labelValor.setBounds(50, 100, 200, 30);
         
        JLabel labelAcoes = new JLabel("Selecione a ação: ");
        labelAcoes.setBounds(50, 170, 150, 30);

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(50, 50, 100, 30);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(50, 120, 150, 30);
        
        
        //adicionar campo para o usuario escrever e Definir tamanho
        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(50, 80, 100, 30);
        
        
        //definindo o campo para o valor a ser investido em reais
        // Cria o JFormattedTextField com o NumberFormatter
        JFormattedTextField campoDinheiro = new JFormattedTextField(formatter);
        campoDinheiro.setValue(0.00); // Define o valor inicial
        campoDinheiro.setBounds(50, 130, 150, 30);
        
                
        //adicionar campo para a senha do usuario e Definir tamanho
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(50, 150, 150, 30);
        
        
        //criando uma aba pra janela2
        JTabbedPane abasInvestimentos = new JTabbedPane();
        abasInvestimentos.setBounds(300, 50, 800, 350);
        janela2.add(abasInvestimentos);
        
         //criando a aba do hitorico
        JPanel painelHistorico = new JPanel();
        painelHistorico.setLayout(null);

        JTextArea textoHistorico = new JTextArea();
        textoHistorico.setEditable(false);
        textoHistorico.setBounds(20, 20, 750, 300);

        painelHistorico.add(textoHistorico);

        // adiciona a aba do historico
        abasInvestimentos.addTab("Histórico", painelHistorico);

        
        
        //criando o saldo que a pessoa vai ter depois que logar ****************
        // cria o saldo inicial
        final double[] saldo = {100.0};
        JLabel labelSaldo = new JLabel("Saldo: " + moedaformat.format(saldo[0]));
        labelSaldo.setBounds(950, 10, 300, 30);

        
        
        // adicionando coisas a janela1 com o add
        janela1.add(botaoLogar);
        janela1.add(labelUsuario);
        janela1.add(campoUsuario);
        janela1.add(campoSenha);
        janela1.add(labelSenha);
        janela1.add(botaoReset);
        janela1.add(labelTitulo1);
        janela1.add(botaoCriar);
        //definindo o layout da janela1 como nulo (para podermos trocar o layout dela ou melhor tamanho das coisas)
        janela1.setLayout(null);
        janela1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame.EXIT_ON_CLOSE é uma constante = 3;
        janela1.setVisible(true);
        
        
        //Botoes Abaixo >>>>>>>>>>>>>>
        //adiciona uma ação pra quando você apertar o "botao"
        botaoLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                if (usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(janela1, "Preencha usuário e senha!");
                    return;
                }

                LoginDAO loginDAO = new LoginDAO();
                boolean autenticado = loginDAO.autenticar(usuario, senha);

                if (autenticado) {
                    JOptionPane.showMessageDialog(janela1, "Login efetuado com sucesso!");

                    // fecha a janela de login
                    janela1.setVisible(false);
                    janela1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    // aqui você abre a janela2
                    //janela2.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(janela1, "Usuário ou senha incorretos!");
                }
                if (senha.equals("") || usuario.equals("")) {
                    //janela1.setDefaultCloseOperation(JFrame.);
                    System.out.printf("\nDigite algo nos campos solicitados!!!");
                }else if (autenticado) {
                    janela1.setVisible(false);
                    System.out.printf("Efetuando Login com o Usuario: %s\nSenha: %s", usuario, senha);
                    campoUsuario.setText("");
                    campoSenha.setText("");
                    //definindo um parametro para só fechar a janela1 quando apertar o x
                    janela1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    // janela 2
                    janela2.setLayout(null);
                    janela2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame.EXIT_ON_CLOSE é uma constante = 3;
                    janela2.setVisible(true);
                    janela2.add(labelTitulo2);
                    janela2.add(botaoAplicar);
                    janela2.add(labelValor);
                    janela2.add(campoDinheiro);
                    janela2.add(listaAcoes);
                    janela2.add(labelAcoes);
                    janela2.add(labelSaldo);
                    janela2.add(labelIdeposito);
                    janela2.add(labelIdeposito2);
                    janela2.add(labelIdeposito3);
                    
                    botaoAplicar.addActionListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //transformando o item da JBOX em String
                            String itemSelecionado = (String) listaAcoes.getSelectedItem();
                            System.out.println("\nAcao selecionada: "+itemSelecionado);
                            //transformando o valor do dinheiro em double :(
                            Object valorObjeto = campoDinheiro.getValue();
                            
                            if (valorObjeto instanceof Number) {
                                Number valorNumero = (Number) valorObjeto;
                                double valorInvestido = valorNumero.doubleValue();

                                if (valorInvestido > saldo[0]) {
                                    JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                                    return;
                                }
                                else if (valorInvestido == 0){
                                    JOptionPane.showMessageDialog(null, "Digite um valor diferente de 0");
                                    return;
                                }
                                //retira temporariamente do saldo o valr investido
                                saldo[0] -= valorInvestido;
                                labelSaldo.setText("Saldo: " + moedaformat.format(saldo[0]));
                                
                                //adiciona no historico o valor do investimento
                                historico.add("Comprou " + itemSelecionado + " por " + moedaformat.format(valorInvestido));

                                double valorDouble = valorNumero.doubleValue();
                                //String acao = listaAcoes.toString();
                                System.out.println("\nValor como double: " + valorDouble);
                                
                                // Criando um painel novo para essa ação
                                JPanel painelAba = new JPanel();
                                painelAba.setLayout(null);

                                JLabel info = new JLabel("Investimento em " + itemSelecionado + 
                                    " de " + moedaformat.format(valorDouble));
                                info.setBounds(20, 20, 400, 30);
                                painelAba.add(info);
                                
                                //adicionando o gráfico na aba de investimentos
                                PainelGrafico grafico = new PainelGrafico(valorDouble);
                                grafico.setBounds(20, 60, 800, 250);
                                painelAba.add(grafico);
                                //aqui é o botão de vender a ação que você comprou
                                JButton botaoVender = new JButton("Vender");
                                botaoVender.setBounds(600, 20, 100, 30);
                                painelAba.add(botaoVender);
                                
                                //adicionando os valores no historico
                                //colocando os valores de compra no historico
                                textoHistorico.setText(String.join("\n", historico));
                                
                                botaoVender.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                        int indice = abasInvestimentos.indexOfComponent(painelAba);
                                        if (indice != -1) {
                                            double valorAtual = grafico.getUltimoValor();

                                            // calcula a variação percentual em relação ao valor base 100 do gráfico
                                            double variacaoPercentual = (valorAtual - 100) / 100.0;

                                            // aplica essa variação sobre o valor investido
                                            double valorFinal = valorDouble * (1 + variacaoPercentual);
                                            saldo[0] += valorFinal;
                                            
                                            labelSaldo.setText("Saldo: " + moedaformat.format(saldo[0]));
                                            
                                            
                                            //adiciona algumas informações no historico
                                            double lucro = valorFinal - valorDouble;

                                            historico.add(
                                                "Vendeu " + itemSelecionado +
                                                " por " + moedaformat.format(valorFinal) +
                                                " (Lucro/Prejuízo: " + moedaformat.format(lucro) + ")"
                                            );
                                            textoHistorico.setText(String.join("\n", historico));

                                            textoHistorico.setText(String.join("\n", historico));


                                            // Fecha a aba após vender
                                            abasInvestimentos.remove(painelAba);
                                            //abasInvestimentos.remove(painelHistorico);

                                            System.out.println("Ação vendida. Lucro/Prejuízo: " + valorFinal);
                                        }
                                    }
                                });


                                // Adiciona uma nova aba no TabbedPane
                                abasInvestimentos.addTab(itemSelecionado, painelAba);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                                return; // cancela a compra
                            }
                        }
                    });

                    //janela 3
                    janela3.setLayout(null);
                    janela3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // JFrame.EXIT_ON_CLOSE é uma constante = 3;
                    janela3.setVisible(true);
                    janela3.add(labelTitulo3);
                    //adicionando o gráfico de colunas na janela
                    String[] nomesAcoes = {"Ouro", "Petróleo", "Bitcoin", "Ações BR", "FIIs", "ETFs", "Ações EUA"};

                    GraficoColunas grafico3 = new GraficoColunas(nomesAcoes);
                    grafico3.setBounds(50, 80, 800, 350);

                    janela3.add(grafico3);

                    //janela 4
                    janela4.setLayout(null);
                    janela4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // JFrame.EXIT_ON_CLOSE é uma constante = 3;
                    janela4.setVisible(true);
                    janela4.add(labelTitulo4);
                    // -------- SISTEMA DE NOTÍCIAS --------

                    // Títulos das notícias
                    String[] titulosNoticias = {
                        "NASA lança campanha de apostas de foguete: \"Vamos estimular a aposta espacial começando agora\", pontuam cientistas do órgão",
                        "Estudantes entram em desespero após retirada espontânea de Inteligências Artificiais do Brasil: \'resume pra mim', ou só mandam uma foto, já chega disso!\", desabafam as IAs",
                        "Mediante crise na Argentina a felicidade cresce cerca de 1% no Brasil, \"É sempre bom saber que tem alguém pior do que você né?\", indaga um morador de rua em Anta Gorda(RS).",
                        "Putin inicia campanha nuclear em resposta a comentário ofensivo de Trump sobre sua mãe: \"Tá trolando?\", disparou o presidente",
                        "Rinha de mascotes de loja movimenta apostas no Brasil \"Estou louco para ver o frango da Sadia apanhar pros \"Peru\" do Perdigão\" Dispara comerciante",
                        "Tráfico de humanos na Líbia diminui após absolvição do ministro da Segurança do país: \"É só não comprar\", declarou o governante"
                    };

                    // Caminho das imagens
                    String[] imagensNoticias = {
                        "noticias/nasa_not_1.jpg",
                        "noticias/estudantes_not_1.jpg",
                        "noticias/crise_not_1.jpg",
                        "noticias/putin_not_1.jpg",
                        "noticias/rinha_not_1.jpg",
                        "noticias/libia_not_1.jpg"
                    };

                    // Label para imagem
                    JLabel labelImagem = new JLabel();
                    labelImagem.setBounds(25, 45, 384, 384);
                    janela4.add(labelImagem);

                    // Label para título
                    JLabel labelTituloNoticia = new JLabel();
                    labelTituloNoticia.setBounds(425, 45, 450, 75);
                    janela4.add(labelTituloNoticia);

                    // Índice da notícia atual
                    final int[] indice = {0};

                    // Função para trocar notícia
                    Runnable trocarNoticia = () -> {
                        labelTituloNoticia.setText("<html>" + titulosNoticias[indice[0]] + "</html>");
                        labelImagem.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader()
                            .getResource(imagensNoticias[indice[0]])));

                        indice[0] = (indice[0] + 1) % titulosNoticias.length;
                    };

                    // Executa a primeira imediatamente
                    trocarNoticia.run();

                    // Timer para trocar a cada 10 segundos
                    new javax.swing.Timer(10000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            trocarNoticia.run();
                        }
                    }).start();

                }
            }
        });

        botaoReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                campoUsuario.setText("");
                campoSenha.setText("");
            }
        });
        
        
        botaoCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                if (usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(janela1, "Preencha usuário e senha!");
                    return;
                }

                try {
                    Connection con = ConnectionFactory.conectar();
                    String sql = "INSERT INTO login (nome, senha) VALUES (?, ?)";

                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, usuario);
                    stmt.setString(2, senha);

                    stmt.executeUpdate();
                    con.close();

                    JOptionPane.showMessageDialog(janela1, "Usuário criado com sucesso!");

                    campoUsuario.setText("");
                    campoSenha.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(janela1, "Erro ao criar usuário! Talvez esse nome já exista.");
                }
            }
        });

        //Botoes acima ^^^^^^^^^^^^^^^^
    }
}
