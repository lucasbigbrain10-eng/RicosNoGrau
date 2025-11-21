package com.mycompany.janela_de_investimentos;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class PainelGrafico extends JPanel {
    private final ArrayList<Integer> valores = new ArrayList<>();
    private final Random random = new Random();
    private final int maxValor = 200;
    private final int minValor = 50;
    private double valorInicial;


    public PainelGrafico(double valorInicial) {
        this.valorInicial = valorInicial;

        setBackground(Color.WHITE);

        for (int i = 0; i < 30; i++) {
            valores.add(100 + random.nextInt(40)); // valores entre 100 e 140
        }

        Timer timer = new Timer(1000, e -> {
            int ultimo = valores.get(valores.size() - 1);
            int novoValor = ultimo + random.nextInt(11) - 5; // sobe ou desce
            if (novoValor < minValor) novoValor = minValor;
            if (novoValor > maxValor) novoValor = maxValor;
            valores.add(novoValor);
            if (valores.size() > 50) valores.remove(0);
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint grad = new GradientPaint(
            0, 0, new Color(25, 25, 25),
            0, getHeight(), new Color(15, 15, 15)
        );
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g2.setPaint(grad);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // grade
        g2.setColor(new Color(60, 60, 60));
        for (int i = 0; i <= 10; i++) {
            int y = i * getHeight() / 10;
            g2.drawLine(0, y, getWidth(), y);
        }
        for (int i = 0; i <= 10; i++) {
            int x = i * getWidth() / 10;
            g2.drawLine(x, 0, x, getHeight());
        }

        // eixo lateral
        g2.setColor(new Color(200, 200, 200));
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 0; i <= 10; i++) {
            int valor = maxValor - (i * (maxValor - minValor) / 10);
            int y = i * getHeight() / 10;
            g2.drawString("R$" + valor, 5, y + 12);
        }

        // gráfico
        for (int i = 1; i < valores.size(); i++) {
            int x1 = (i - 1) * getWidth() / 50;
            int y1 = getHeight() - mapearValor(valores.get(i - 1), getHeight());
            int x2 = i * getWidth() / 50;
            int y2 = getHeight() - mapearValor(valores.get(i), getHeight());

            if (valores.get(i) > valores.get(i - 1)) g2.setColor(new Color(80, 255, 80));   // verde neon
            else if (valores.get(i) < valores.get(i - 1)) g2.setColor(new Color(255, 70, 70)); // vermelho neon
            else g2.setColor(new Color(90, 180, 255)); // azul claro


            g2.setStroke(new BasicStroke(2f));
            g2.drawLine(x1, y1, x2, y2);
        }

        // ponto final
        int ultimoValor = valores.get(valores.size() - 1);
        int x = getWidth() - 80;
        int y = getHeight() - mapearValor(ultimoValor, getHeight());
        g2.setColor(new Color(255, 80, 80));
        g2.fillOval(x - 5, y - 5, 10, 10);

        g2.setColor(new Color(230, 230, 230));
        g2.drawString("R$" + ultimoValor, x - 50, y - 10);
    }

    public int getUltimoValor() {
        return valores.get(valores.size() - 1);
    }

    private int mapearValor(int valor, int alturaPainel) {
        return (int) ((double)(valor - minValor) / (maxValor - minValor) * alturaPainel);
    }
    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }
}
