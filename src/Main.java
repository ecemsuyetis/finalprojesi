import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Ders {
    private String dersKodu;
    private String dersAd;
    private String dersDonem;
    private String ogretimGorevlisi;

    public Ders(String dersKodu, String dersAd, String dersDonem, String ogretimGorevlisi) {
        this.dersKodu = dersKodu;
        this.dersAd = dersAd;
        this.dersDonem = dersDonem;
        this.ogretimGorevlisi = ogretimGorevlisi;
    }

    // Getter ve setter metotları buraya eklenmeli

    @Override
    public String toString() {
        return "Ders{" +
                "dersKodu='" + dersKodu + '\'' +
                ", dersAd='" + dersAd + '\'' +
                ", dersDonem='" + dersDonem + '\'' +
                ", ogretimGorevlisi='" + ogretimGorevlisi + '\'' +
                '}';
    }
}

class DersForm extends JFrame {
    private JTextField dersKoduField;
    private JTextField dersAdField;
    private JTextField dersDonemField;
    private JTextField ogretimGorevlisiField;
    private JButton kaydetButton;

    public DersForm() {
        setTitle("Ders Formu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dersKoduField = new JTextField();
        dersAdField = new JTextField();
        dersDonemField = new JTextField();
        ogretimGorevlisiField = new JTextField();
        kaydetButton = new JButton("Kaydet");

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ders ders = new Ders(
                        dersKoduField.getText(),
                        dersAdField.getText(),
                        dersDonemField.getText(),
                        ogretimGorevlisiField.getText()
                );
                saveToJSON(ders);
                JOptionPane.showMessageDialog(null, "Ders kaydedildi.");
            }
        });

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Ders Kodu:"));
        add(dersKoduField);
        add(new JLabel("Ders Adı:"));
        add(dersAdField);
        add(new JLabel("Ders Dönemi:"));
        add(dersDonemField);
        add(new JLabel("Öğretim Görevlisi:"));
        add(ogretimGorevlisiField);
        add(new JLabel(""));
        add(kaydetButton);

        setLocationRelativeTo(null);
    }

    private void saveToJSON(Ders ders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ders.json", true))) {
            String json = ders.toString();
            writer.write(json);
            writer.newLine();  // Her kayıt arasına bir satır boşluk ekleyelim
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MainForm extends JFrame {
    private JButton dersButton;

    public MainForm() {
        setTitle("Ana Menü");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dersButton = new JButton("Ders Formu");

        dersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DersForm().setVisible(true);
            }
        });

        setLayout(new FlowLayout());
        add(dersButton);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
}