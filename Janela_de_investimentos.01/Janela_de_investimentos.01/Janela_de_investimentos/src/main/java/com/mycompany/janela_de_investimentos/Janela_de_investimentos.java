package com.mycompany.janela_de_investimentos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.text.NumberFormatter;

public class Janela_de_investimentos {

    public static void main(String[] args) {
        //definindo a moeda como real 
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        format.setMaximumFractionDigits(2);
        
        
        //lista das ações de investimento
        String[] Acoes = {"Ouro", "Petróleo", "Bitcoin", "Ações Brasileiras", "Fundos Imobiliários", "ETFs", "Ações Americanas"};
        //criando a JComboBox que é a famosa caixa de seleção
        JComboBox listaAcoes = new JComboBox(Acoes);
        listaAcoes.setSelectedIndex(4);
        listaAcoes.setBounds(50, 200, 150, 30);
        
        
        // Cria um NumberFormatter com o formatador
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false); // Não permite caracteres inválidos
        formatter.setOverwriteMode(true); // Sobrescreve o texto em vez de inseri-lo

        // adicionar uma janela e Definir tamanho
        JFrame janela1 = new JFrame();
        janela1.setBounds(0, 0, 400, 300);
        janela1.getContentPane().setBackground(Color.green);

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
        
        JLabel labelValor = new JLabel("Valor a ser depositado: ");
        labelValor.setBounds(50, 100, 150, 30);
        
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
        
        
        //criando o saldo que a pessoa vai ter depois que logar ****************
        double saldo = 0;
        JLabel labelSaldo = new JLabel("Saldo: "+format.format(saldo));
        labelSaldo.setBounds(1000,-20,100,100);
        
        
        // adicionando coisas a janela1 com o add
        janela1.add(botaoLogar);
        janela1.add(labelUsuario);
        janela1.add(campoUsuario);
        janela1.add(campoSenha);
        janela1.add(labelSenha);
        janela1.add(botaoReset);
        janela1.add(labelTitulo1);
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
                if (senha.equals("") || usuario.equals("")) {
                    //janela1.setDefaultCloseOperation(JFrame.);
                    System.out.printf("\nDigite algo nos campos solicitados!!!");
                }else {
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
                                double valorDouble = valorNumero.doubleValue();
                                //String acao = listaAcoes.toString();
                                System.out.println("\nValor como double: " + valorDouble);
                                
                                // Criando um painel novo para essa ação
                                JPanel painelAba = new JPanel();
                                painelAba.setLayout(null);

                                JLabel info = new JLabel("Investimento em " + itemSelecionado + 
                                    " de " + format.format(valorDouble));
                                info.setBounds(20, 20, 400, 30);
                                painelAba.add(info);
                                
                                //adicionando o gráfico na aba de investimentos
                                PainelGrafico grafico = new PainelGrafico();
                                grafico.setBounds(20, 60, 800, 250);
                                painelAba.add(grafico);
                                //aqui é o botão de vender a ação que você comprou
                                JButton botaoVender = new JButton("Vender");
                                botaoVender.setBounds(600, 20, 100, 30);
                                painelAba.add(botaoVender);

                                botaoVender.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int indice = abasInvestimentos.indexOfComponent(painelAba);
                                        if (indice != -1) {
                                            abasInvestimentos.remove(indice); // fecha a aba
                                            System.out.println("Ação vendida e aba fechada.");
                                        }
                                    }
                                });

                                // Adiciona uma nova aba no TabbedPane
                                abasInvestimentos.addTab(itemSelecionado, painelAba);
                            }
                        }
                    });

                    //janela 3
                    janela3.setLayout(null);
                    janela3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame.EXIT_ON_CLOSE é uma constante = 3;
                    janela3.setVisible(true);
                    janela3.add(labelTitulo3);

                    //janela 4
                    janela4.setLayout(null);
                    janela4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame.EXIT_ON_CLOSE é uma constante = 3;
                    janela4.setVisible(true);
                    janela4.add(labelTitulo4);
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

        //Botoes acima ^^^^^^^^^^^^^^^^
    }
}
