package main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Maze {
	final int gridsWid=50;
	final int gridHeg=50;
	JLabel[][] labels;
	char[][] maze;
	public Maze(int row,int col,int[] pos,int mode){
		JFrame f=new JFrame("迷宫设计");
		f.setBounds(650, 270, 50*col+10, 50*row+35);
		f.setBackground(null);
		labels=new JLabel[row][col];
		maze=new char[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				maze[i][j]='.';
				if(i==row-1&&j==col-1&&mode==0)break;
				labels[i][j]=new JLabel();
				labels[i][j].setSize(gridsWid,gridHeg);
				//setLocation是按照坐标形式使用的
				labels[i][j].setLocation(j*gridsWid,i*gridHeg);
				labels[i][j].setBackground(Color.white);
				labels[i][j].setOpaque(true);
				labels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				f.add(labels[i][j]);
				//MouseListener只接受final常量
				final int a=i;
				final int b=j;
				labels[i][j].addMouseListener(new MouseListener(){
					public void mouseClicked(MouseEvent e) {
						if(maze[a][b]=='.') {labels[a][b].setBackground(Color.black);maze[a][b]='+';System.out.println(a);System.out.println(b);}
						else{labels[a][b].setBackground(Color.white);maze[a][b]='.';}
					}
					public void mousePressed(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
				});
			}
		}
		JLabel l=new JLabel();
		l.setSize(gridsWid,gridHeg);
		l.setLocation(0,0);
		f.add(l);
		
		f.remove(labels[pos[0]][pos[1]]);
		labels[pos[0]][pos[1]]=new JLabel();
		labels[pos[0]][pos[1]].setSize(gridsWid,gridHeg);
		labels[pos[0]][pos[1]].setLocation(pos[0]*gridsWid,pos[1]*gridHeg);
		labels[pos[0]][pos[1]].setBackground(Color.green);
		labels[pos[0]][pos[1]].setOpaque(true);
		f.add(labels[pos[0]][pos[1]]);
		
		if(mode==0) {
			labels[row-1][col-1]=new JLabel();
			labels[row-1][col-1].setSize(gridsWid,gridHeg);
			labels[row-1][col-1].setLocation((col-1)*gridsWid,(row-1)*gridHeg);
			labels[row-1][col-1].setBackground(Color.red);
			labels[row-1][col-1].setOpaque(true);
			f.add(labels[row-1][col-1]);
		}
		f.setVisible(true);
	}
	
	public void paint(int row,int col,ArrayList<ArrayList<Integer>> minRoute) {
		JFrame f=new JFrame("迷宫设计");
		f.setBounds(650, 270, 50*col+10, 50*row+35);
		f.setBackground(null);
		labels=new JLabel[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				labels[i][j]=new JLabel();
				labels[i][j].setSize(gridsWid,gridHeg);
				labels[i][j].setLocation(j*gridsWid,i*gridHeg);
				labels[i][j].setOpaque(true);
				if(maze[i][j]=='+') {labels[i][j].setBackground(Color.black);}
				else {labels[i][j].setBackground(Color.white);}
				f.add(labels[i][j]);
				labels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}
		for(int i=0;i<minRoute.size();i++) {
			labels[minRoute.get(i).get(0)][minRoute.get(i).get(1)].setBackground(Color.blue);
		}
		f.setVisible(true);
	}
	
	/*
	public static void main(String[] args) {
		new Maze(5,9,new int[]{0,0},0);
	}
	*/
}

