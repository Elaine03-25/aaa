package health;
import java.awt.event.*;
import javax.swing.*;
class Id extends JFrame{
	private JPanel contentPane;
	private JTextField txtname,txtpassword; 
	private JLabel name,password; 
	private JButton btnlogin;
	Id(){
		//視窗設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,280,180); //視窗位置和大小
		contentPane=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//建立輸入格(帳號、密碼)
		txtname=new JTextField();
		txtname.setColumns(20);
		txtname.setBounds(100,20,120,25);
		contentPane.add(txtname);
		
		txtpassword=new JTextField();
		txtpassword.setColumns(20);
		txtpassword.setBounds(100,60,120,25);
		contentPane.add(txtpassword);
		//建立提示(帳號、密碼)
		name=new JLabel("帳號:");
		name.setBounds(50,25,100,15);
		contentPane.add(name);
		
		password=new JLabel("密碼:");
		password.setBounds(50,65,100,15);
		contentPane.add(password);
		//建立登入按鈕
		btnlogin=new JButton("登入");
		btnlogin.setBounds(100,100,80,25);
		contentPane.add(btnlogin);
		//當登入按鈕按下時，帳號密碼是否正確
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtname.getText().equals("abcde") && txtpassword.getText().equals("12345")) {
					JOptionPane.showMessageDialog(null, "登入成功！");
					new Outputwindow();
		            dispose();
				}
				else {
		            JOptionPane.showMessageDialog(null, "帳號或密碼錯誤", "錯誤", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		
		setTitle("登入帳號"); //視窗名稱
		setVisible(true); //是否顯示視窗
	}
}

class Outputwindow extends JFrame {
    private JTextField txtheight, txtweight ;
    private JComboBox<String> exercise;
    private JTextArea txtresult; // 用來顯示食譜結果(底下白框)

    public Outputwindow() {
        setTitle("資料輸入與食譜");
        setBounds(100, 100, 400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        //輸入介面
        //身高
        addLabel(panel, "身高(cm):", 30, 20); //提示
        txtheight = new JTextField(); 
        txtheight.setBounds(120, 20, 100, 25); 
        panel.add(txtheight);
        //體重
        addLabel(panel, "體重(kg):", 30, 60); //提示
        txtweight = new JTextField(); 
        txtweight.setBounds(120, 60, 100, 25); 
        panel.add(txtweight);
        //運動量
        addLabel(panel, "運動量:", 30, 100); //提示
        String[] acts = {"1. 輕度", "2. 中度", "3. 重度"}; //選項
        exercise = new JComboBox<>(acts); //下拉選單
        exercise.setBounds(120, 100, 100, 25);
        panel.add(exercise);
        //結果顯示
        txtresult = new JTextArea();
        txtresult.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtresult); //建立捲軸
        scroll.setBounds(30, 220, 320, 200);
        panel.add(scroll);
        //建立計算按鈕
        JButton btnCalc = new JButton("計算");
        btnCalc.setBounds(120, 145, 100, 30);
        panel.add(btnCalc);
        //開始計算
        btnCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double h = Double.parseDouble(txtheight.getText());
                    double w = Double.parseDouble(txtweight.getText());
                    int activity = exercise.getSelectedIndex() + 1; // 1, 2, 3
                    
                    double bmi = w / ((h/100) * (h/100));
                    String res = String.format("你的 BMI 為：%.2f\n", bmi);
                    
                    if (bmi < 18.5) {
                        res += "體型判斷：過輕\n" + getUnderWeightMenu(activity);
                    } else if (18.5<= bmi && bmi < 24) {
                        res += "體型判斷：正常\n" + getNormalMenu(activity);
                    } else if (24<=bmi && bmi < 27) {
                        res += "體型判斷：過重\n" + getOverWeightMenu(activity);
                    } else {
                        res += "體型判斷：肥胖\n" + getObeseMenu(activity);
                    }
                    txtresult.setText(res);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "請輸入正確的數字");
                }
            }
        });
        setVisible(true);
    }
    private void addLabel(JPanel p, String text, int x, int y) {
        JLabel l = new JLabel(text);
        l.setBounds(x, y, 80, 25);
        p.add(l);
    }
    // 食譜
    private String getNormalMenu(int act) {
        String s = "💡建議食譜\n早餐：蛋餅 + 無糖豆漿\n午餐：便當（白飯1碗 + 雞腿/排骨 + 青菜）\n晚餐：地瓜1條 + 煎蛋 + 燙青菜\n";
        if (act == 3) s += "加餐：運動後可加一根香蕉或一杯牛奶";
        return s;
    }

    private String getOverWeightMenu(int act) {
        String s = "💡建議食譜\n早餐：全麥吐司1片 + 煎蛋 + 無糖豆漿\n午餐：便當（飯少 + 滷雞腿 + 青菜2樣）\n晚餐：滷味（豆乾、蛋、海帶）+ 燙青菜\n";
        if (act == 1) s += "提醒：避免含糖飲料與宵夜";
        return s;
    }

    private String getObeseMenu(int act) {
        return "💡建議食譜\n早餐：茶葉蛋2顆 + 黑咖啡或無糖豆漿\n午餐：自助餐（飯半碗 + 雞胸/白切雞 + 青菜2~3樣）\n晚餐：清湯火鍋（蔬菜 + 豆腐 + 雞肉）\n💥注意：避免炸物、加工食品";
    }

    private String getUnderWeightMenu(int act) {
        String s = "💡建議食譜\n早餐：厚片吐司 + 鮮奶或奶茶\n午餐：白飯1.5碗 + 雞腿或排骨 + 青菜\n晚餐：義大利麵或咖哩飯 + 湯品\n";
        if (act >= 2) s += "加餐：下午可加點心（麵包或香蕉）";
        return s;
    }
}

public class A {
	public static void main(String[] args) {
		Id f = new Id();
	}

}
