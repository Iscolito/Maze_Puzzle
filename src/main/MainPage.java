package main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainPage {
	ComponentsSet components;
	public MainPage(){
		JFrame f=new JFrame("迷宫游戏");
		f.setBounds(650, 270, 400, 300);
		f.setBackground(null);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		components=new ComponentsSet();
		components.set(f);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new MainPage();
	}
}

class ComponentsSet{

	//前端组件
    JTextField text1,text2,text3,text4;
    JButton button_reset,button_refresh,button_save,button_search,button_plan;
    JPanel container3,container_text,container4,container_read;
    JRadioButton checkBox1, checkBox2;
    ButtonGroup group;
    String[] readme;
    
    //后端输入
	int row,col;
	int[] pos;
	Maze maze;
	int mode;
    BackEnd findway;
    
    public ComponentsSet(){
    	findway=new BackEnd();
    	pos=new int[2];
    	readme=new String[5];
    	readme[0]=new String("迷宫游戏提供两种模拟方式");
    	readme[1]=new String("一种为出口在边界上，一种为出口在右下角");
    	readme[2]=new String("选择出口位置并输入行数列数与起点后进行设计");
    	readme[3]=new String("设计结束进行规划即可得到最小步数和蓝色标记路径");
    }
    void set(JFrame frame) {
    	//定义组件行
    	container_text=new JPanel(new FlowLayout(FlowLayout.LEFT, 6,6));
    	container3=new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6)); 
    	container4=new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6)); 
    	container_read=new JPanel(new FlowLayout(FlowLayout.LEFT, 6,6));
    	
    	text1=new JTextField(new String("5"),3);
    	container_text.add(new JLabel("行数:"));
    	container_text.add(text1);
    	
    	text2=new JTextField(new String("5"),3);
    	container_text.add(new JLabel("列数:"));
    	container_text.add(text2);
    	
    	container_text.add(new JLabel("起始点:"));
    	text3=new JTextField(new String("0"),2);
    	text4=new JTextField(new String("0"),2);
    	container_text.add(new JLabel("("));
    	container_text.add(text3);
    	container_text.add(new JLabel(","));
    	container_text.add(text4);
    	container_text.add(new JLabel(")"));
    	
        container3.add(new JLabel("按钮:"));
        button_refresh = new JButton("设计");
        button_refresh.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		mode=checkBox1.isSelected()?0:1;
        		String t1=text1.getText();
        		String t2=text2.getText();
        		String trow=text3.getText();
        		String tcol=text4.getText();
    			try {
	        		row=Integer.valueOf(t1);
	        		col=Integer.valueOf(t2);
	        		pos[0]=Integer.valueOf(trow);
	        		pos[1]=Integer.valueOf(tcol);
    			}catch (Exception e1) {
    				JOptionPane.showMessageDialog(null,"输入信息不规范","警告",JOptionPane.WARNING_MESSAGE);
    				return;
    			}
    			if(pos[0]>=row||pos[1]>=col||pos[0]<0||pos[1]<0) {
    				JOptionPane.showMessageDialog(null,"起点在迷宫之外","警告",JOptionPane.WARNING_MESSAGE);
    			}
    			else {maze=new Maze(row,col,pos,mode);}
        	}
        });
        container3.add(button_refresh);

        container3.add(new JLabel("按钮:"));
        button_reset = new JButton("重置");
        container3.add(button_reset);

        container3.add(new JLabel("按钮:"));
        button_save = new JButton("规划");
        button_save.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		if(maze==null) {
        			JOptionPane.showMessageDialog(null,"迷宫还没设计好","警告",JOptionPane.WARNING_MESSAGE);
        		}
        		else {
        			findway.nearestExit(maze.maze, pos,mode);
        			JOptionPane.showMessageDialog(null,String.format("最短路径需要走%d步，如蓝色路径",findway.min),"结果",JOptionPane.INFORMATION_MESSAGE);
        			maze.paint(row, col, findway.minRoute);
        		}
        	}
        });
        container3.add(button_save);
        
        container4.add(new JLabel("选择出口位置:"));
        checkBox1=new JRadioButton(new String("右下角"),true);
        checkBox2=new JRadioButton(new String("边界"),false);
        group=new ButtonGroup();
        group.add(checkBox1);
        group.add(checkBox2);
        container4.add(checkBox1);
        container4.add(checkBox2);
        

        container_read.add(new JLabel(readme[0]));
        container_read.add(new JLabel(readme[1]));
        container_read.add(new JLabel(readme[2]));
        container_read.add(new JLabel(readme[3]));
        
        //Layout采取绝对布局
        container3.setBounds(0,0,300,50);
        container_text.setBounds(10,50,300,30);
        container4.setBounds(10,80,300,50);
        container_read.setBounds(10,130,300,100);
        frame.add(container3);
        frame.add(container_text);
        frame.add(container4);
        frame.add(container_read);
    }
}
