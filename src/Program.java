import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.nio.file.Paths;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        String path = "C://framedatapoints.txt";
        List<String> inputList = readFile(path);
        MyFrame frame = new MyFrame(splitString(inputList),cluster(splitString(inputList)), clusterCenter(splitString(inputList)));// передаём в качестве аргумента список классов
        System.out.println(clusterCenter(splitString(inputList)));
    }

    //построчное чтение данных из файла
    public static List<String> readFile(String path){
        List<String> list = new ArrayList<>();
        try{
            list = Files.readAllLines(Paths.get(path));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    //разбиение строк на отдельные элементы и их запись в класс Point
    public static List<Point> splitString(List<String> list){
        int listLength = list.size();
        List<Point> pointsList = new ArrayList<Point>(); //создаем список классов
        for(int i = 0; i < listLength; i ++){
            String[] splitString = list.get(i).split("\\s"); //разделяем строку на последовательности символов
            Point p = new Point();
            p.setpX(Integer.parseInt(splitString[0]));
            p.setpY(Integer.parseInt(splitString[1]));
            pointsList.add(p);
        }
        return pointsList;
    }

    //определение кластера на изображении
    public static boolean cluster(List<Point> points){
        boolean clustered = false;
        for(int j = 1; j < points.size(); j++){
            Point p1 = points.get(j-1);
            Point p2 = points.get(j);
            boolean x1 = (((p1.getpX())-(p2.getpX())) >= 50);
            boolean x2 = (((p2.getpX())-(p1.getpX())) >= 50);
            boolean y1 = (((p1.getpY())-(p2.getpY())) >= 50);
            boolean y2 = (((p2.getpY())-(p1.getpY())) >= 50);
            if((x1 || x2) && (y1 || y2)){
                clustered = true;
            }
            else {
                clustered = false;
            }
        }
        return clustered;
    }

    //определение центра кластера
    public static List<Point>  clusterCenter(List<Point> points){
        List<Point> listCenterPoints = new ArrayList<Point>();
        int averageX = 0;
        int averageY = 0;
        for (int j = 0; j < points.size(); j++){
            Point clusterCenter = new Point();
            Point p = points.get(j);
            averageX = +p.getpX();
            averageY = +p.getpY();
            clusterCenter.setpX((averageX / points.size()));
            clusterCenter.setpY((averageY / points.size()));
            listCenterPoints.add(clusterCenter);
        }
        String testListCenterPoints = listCenterPoints.toString();
        return listCenterPoints;
    }
}