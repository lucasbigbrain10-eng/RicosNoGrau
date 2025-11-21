package com.mycompany.janela_de_investimentos;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GraficoColunas extends JPanel {

    private String[] nomes;
    private double[] valores;
    private Random random = new Random();
    private int mouseX = -1;
    private int mouseY = -1;


    public GraficoColunas(String[] nomes) {
        this.nomes = nomes;
        this.valores = new double[nomes.length];
        // permite detectar o mouse
        setToolTipText(""); 
        //atualizar a posição do mouse
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                // força Swing a mostrar tooltip sempre atualizado
                ToolTipManager.sharedInstance().mouseMoved(e);
            }
        });


        // gerar valores iniciais
        for (int i = 0; i < valores.length; i++) {
            valores[i] = 50 + random.nextInt(50);//valores de 50 a 100
        }

        setPreferredSize(new Dimension(800, 400));

        // TIMER DINÂMICO - atualiza a cada 500ms (0.5s), 4000 (4s)
        Timer timer = new Timer(4000, e -> atualizarValores());
        timer.start();
    }
    //define o que vai aparecer quando o mouse estiver em cima de determinada coluna
    @Override
    public String getToolTipText(java.awt.event.MouseEvent e) {

        int largura = getWidth();
        int altura = getHeight();
        int larguraBarra = largura / (valores.length * 2);

        for (int i = 0; i < valores.length; i++) {

            int x = (i * (larguraBarra * 2)) + 50;
            int yMax = altura - 40;

            // se o mouse está dentro da largura da coluna...
            if (e.getX() >= x && e.getX() <= x + larguraBarra) {
                return nomes[i] + " — Valor atual: " + String.format("R$%.2f", valores[i]);
            }
        }

        return null;
    }

    private void atualizarValores() {
        for (int i = 0; i < valores.length; i++) {

            // variação aleatória entre -5% e +5%
            double variacao = (random.nextDouble() * 10 - 5) / 100.0;
            valores[i] += valores[i] * variacao;

            // trava valor mínimo em 10 e máximo em 200 só pra ficar bonito
            if (valores[i] < 10) valores[i] = 10;
            if (valores[i] > 200) valores[i] = 200;
        }

        repaint(); // redesenha o gráfico
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int largura = getWidth();
        int altura = getHeight();

        int larguraBarra = largura / (valores.length * 2);
        int maxAltura = altura - 80;

        double maior = 0;
        for (double v : valores) if (v > maior) maior = v;

        for (int i = 0; i < valores.length; i++) {

            int x = (i * (larguraBarra * 2)) + 50;
            int alturaBarra = (int) ((valores[i] / maior) * maxAltura);
            int y = altura - alturaBarra - 40;

            // cor única por coluna
            g2.setColor(new Color(80 + i * 20, 100 + i * 10, 200 - i * 15));
            g2.fillRect(x, y, larguraBarra, alturaBarra);

            // borda
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, larguraBarra, alturaBarra);

            // valor acima da barra
            g2.drawString(String.format("R$%.1f", valores[i]), x + 5, y - 5);

            // nome abaixo
            g2.drawString(nomes[i], x, altura - 20);
        }
    }
}
