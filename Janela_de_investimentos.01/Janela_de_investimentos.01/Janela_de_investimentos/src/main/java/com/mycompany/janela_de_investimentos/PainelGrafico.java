package com.mycompany.janela_de_investimentos;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class PainelGrafico extends JPanel {
    // final = não pode ter seu valor alterado após a inicialização (final)
    private final ArrayList<Integer> valores = new ArrayList<>();
    private final Random random = new Random();
    
    //Gera o Painel de fundo do gráfico
    public PainelGrafico() {
        setBackground(Color.WHITE);
        // Gera valores iniciais
        for (int i = 0; i < 30; i++) {
            valores.add(100 + random.nextInt(40)); // entre 100 e 140
        }

        // Atualiza o gráfico a cada 1 segundo
        Timer timer = new Timer(1000, e -> {
            int ultimo = valores.get(valores.size() - 1);
            int novoValor = ultimo + random.nextInt(11) - 5; // sobe ou desce
            if (novoValor < 50) novoValor = 50;
            if (novoValor > 200) novoValor = 200;
            valores.add(novoValor);
            if (valores.size() > 50) valores.remove(0); // limita o tamanho da lista
            repaint(); // redesenha o gráfico
        });
        timer.start();
    }

    @Override
    // desenha as lisnhas principais do gráfico
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        // desenha a linha
        for (int i = 1; i < valores.size(); i++) {
            int x1 = (i - 1) * 10;
            int y1 = getHeight() - valores.get(i - 1);
            int x2 = i * 10;
            int y2 = getHeight() - valores.get(i);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
