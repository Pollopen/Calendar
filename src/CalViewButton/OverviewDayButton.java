package CalViewButton;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import controller.StateMachine;
import views.WindowPanel;

public class OverviewDayButton extends JButton implements ActionListener {
	private Border etchedBorder;
	private String date;
	private StateMachine SM;
	private WindowPanel wp;
	public OverviewDayButton(String text, String date, boolean hasEvent, StateMachine SM, WindowPanel wp) {
		super(text);
		etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		this.date=date;
		this.SM=SM;
		this.wp=wp;
		addActionListener(this);
		setBackground(new Color(200,200,200));
		setMargin(new Insets(0, 0, 0, 0));
		setToolTipText("Mer event finns!");
		if(date.equals(SM.getEasyDate())){
			setForeground(Color.BLUE);
		}
		if(hasEvent){
			setBackground(new Color(150,150,150));
			setOpaque(true);
		}
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(date);
		SM.setEasyDate(date);
		wp.getViewViewer();
		wp.getViewChoice();
		wp.getOverview();
	}

}