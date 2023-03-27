package graphique;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRule extends JPanel {
	private JTextField rule;
	private String valueRule;
	public String getValueRule() {
		return valueRule;
	}
	public void setValueRule(String valueRule) {
		this.valueRule = valueRule;
	}
	public PanelRule() {
//		this.setPreferredSize(new Dimension(10, 20));
		this.setMaximumSize(new Dimension(10, 20));		
		this.setLayout(new GridLayout(2,1));
		rule=new JTextField();
		String []s = {"game of Life","automate Cellulaire","other"};
		JComboBox<String> cb= new JComboBox<>(s);
		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					rule.setText(cb.getSelectedItem().toString());
			}
		});
		this.setValueRule(rule.getText());
		this.add(rule);
		this.add(cb);
//		this.resize(10, 20);		
	}
	public JTextField getRule() {
		return this.rule;
	}
}
