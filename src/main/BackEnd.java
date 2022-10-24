package main;

import java.util.ArrayList;

class BackEnd {
    int min;
    ArrayList<ArrayList<Integer>> layer;
    ArrayList<ArrayList<Integer>> temp;
    ArrayList<ArrayList<Integer>> minRoute;
    ArrayList<ArrayList<ArrayList<Integer>>> route;
    ArrayList<ArrayList<ArrayList<Integer>>> tempr;
    public ArrayList<ArrayList<Integer>> arrayCopy(ArrayList<ArrayList<Integer>> ori){
        ArrayList<ArrayList<Integer>> tgt=new ArrayList<>();
        for(int i=0;i<ori.size();i++){
            tgt.add(ori.get(i));
        }
        return tgt;
    }
    public void findWay(int row, int col,char[][] maze,int layer){
        if(row>=0&&col>=0&&row<maze.length&&col<maze[0].length&&maze[row][col]=='.'){
            ArrayList<Integer> step=new ArrayList<>(){{add(row);add(col);}};
            maze[row][col]='*';
            temp.add(step);
            tempr.add(arrayCopy(route.get(layer)));
            tempr.get(tempr.size()-1).add(step);
        }
    }
    public ArrayList<ArrayList<ArrayList<Integer>>> routeCopy(ArrayList<ArrayList<ArrayList<Integer>>> ori){
        ArrayList<ArrayList<ArrayList<Integer>>> tgt=new ArrayList<>();
        for(int i=0;i<ori.size();i++){
            tgt.add(arrayCopy(ori.get(i)));
        }
        return tgt;
    }

    public int nearestExit(char[][] maze, int[] entrance, int mode) {
        int res=-1,heg=maze.length,len=maze[0].length;
        boolean out=false;
        layer=new ArrayList<>();
        temp=new ArrayList<>();
        route=new ArrayList<>();
        layer.add(new ArrayList<Integer>(){{add(entrance[0]);add(entrance[1]);}});
        route.add(arrayCopy(layer));
        maze[entrance[0]][entrance[1]]='*';
        while (!out){
            temp=new ArrayList<>();
            tempr=new ArrayList<>();
            if(layer.size()==0){return -1;}
            for(int i=0;i<layer.size();i++){
                int row=layer.get(i).get(0),col=layer.get(i).get(1);
                out=mode==0?col==len-1&&row==heg-1:col==len-1||col==0||row==heg-1||row==0;
                if(out) {
                    minRoute=route.get(i);
                    System.out.println(minRoute);
                    break;
                }
                else{
                    findWay(row-1,col,maze,i);
                    findWay(row+1,col,maze,i);
                    findWay(row,col-1,maze,i);
                    findWay(row,col+1,maze,i);
                }
            }res++;
            layer=arrayCopy(temp);
            route=routeCopy(tempr);
        }
        min=res;
        return res;
    }

    /*
    public static void main(String[] args){
        char[][] b =new char[3][4];
        b[0][0]=b[0][1]=b[0][3]=b[1][3]=b[2][0]=b[2][1]=b[2][2]='+';
        b[0][2]=b[1][0]=b[1][1]=b[1][2]=b[2][3]='.';
        BackEnd a=new BackEnd();
        System.out.println(a.nearestExit(b, new int[]{1,2},0));
    }
    */

}