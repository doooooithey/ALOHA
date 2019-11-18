import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
 
public class Aloha extends JFrame{
	//վ�����Ϊ1
	public static int base = 1;
	//վ������
	public int MaxStation;
	//ʱ�����Ҫ���ñȽϳ�����Ȼÿ��ʱ��۵ĳ�ͻ�Ŀ��ܴܺ�Ҳ���޷�������
	public final int randTime = 1000;
	//successPostԽ��Խ�ӽ���ʵ���������ҲԽ����
	private int successPost = 1000;
	//ʱ��۴�С
	public final int time = 2;
	//֡������
	public int countPoint = 0;
	//վ����
	private List<Data> list;
	//����
	private Graphics g;
	
	//���������վ����
	public void setNum(int num){
		this.MaxStation = num;
	}
	
	//���溯��
	public void GetStatus() {
		Random r = new Random();
		int total = 0;
		int success = 0;
		list = new ArrayList<Data>();
		
		//�������վ�㷢������ʱ��
		for (int i = 0; i < MaxStation; i++) {
			Data d = new Data(r.nextInt(randTime)+1);
			list.add(d);
		}
		
		//����ʱ������
		Comparator<Data> comparator = new Comparator<Data>() {
			public int compare(Data d1, Data d2) {					
					return d1.time - d2.time;
			}			
		};		
		Collections.sort(list,comparator);
		
		int count = 0;			//��¼��������ʱ������
		while(true){			
			int temcount = 0;	//��¼ÿ��ʱ��۷���֡�Ĵ���
			for(int i = 0; i < MaxStation; i++){
				if(list.get(i).time >= count * time && list.get(i).time <= (count+1)*time){
					total += 1;
					temcount += 1;
				}else{
					break;
				}
			}
			count++;
			if(temcount == 0){	//û��֡
				//continue;
			}else if(temcount == 1){	//�ɹ���������
				success += 1;
				list.get(0).set(r.nextInt(randTime)+1+count*time);
				if(success > successPost){
					break;
				}
			}else if(temcount > 1){		//��ͻ�������������������ʱ��
				for(int j = 0; j < temcount; j++){
					list.get(j).set(r.nextInt(randTime)+1+count*time);
				}
			}
			Collections.sort(list,comparator);
		}
		drawPoint((int)((1.0 * total/count)* 70 + 100), (int)(400-(10.0 * success/count)*29));
//		System.out.println("ÿ��ʱ�ĳ��Դ�����"+(1.0 * total/count));
//		System.out.println("��������"+(1.0*success/count));			
	}
 
	public static void main(String[] args) {
		Aloha aloha = new Aloha();
		aloha.initUI();     //��ʼ��UI
		int i = 0;
		while(true){
			i++;
			aloha.setNum(base*i);	//����վ����
			aloha.GetStatus();
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		draw(g);
	}
	//��ʼ������
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(100, 400, 500, 400);
		g.drawLine(100,100,100,400);
		g.drawString("0", 90, 405);
		
		for(int i = 1; i <= 5; i++){
			g.drawString("|", 100+70*i, 398);
			g.drawString(i+"", 100+70*i, 413);
		}
		g.drawString("G(ÿ��ʱ���Դ���)", 260,430);
		
		
		for(int i = 1; i <= 10; i++){
			g.drawString("-", 100 , 400-29*i);
			if(i != 10){
				g.drawString("0."+i, 80 , 400-29*i);
			}else{
				g.drawString(i/10+".0", 80 , 400-29*i);
			}
		}		
		int Stringx = 60;
		int Stringy = 180;
		g.drawString("ÿ", Stringx, Stringy);
		g.drawString("��", Stringx, Stringy+14);
		g.drawString("ʱ", Stringx, Stringy + 14*2);
		g.drawString("��", Stringx, Stringy + 14*3);
		g.drawString("��", Stringx, Stringy + 14*4);
		g.drawString("��", Stringx, Stringy + 14*5);
		g.drawString("��", Stringx, Stringy + 14*6);
		g.drawString("S", Stringx, Stringy + 14*7+10);
		g.setColor(Color.BLACK);
	}
	
	//����
	public void drawPoint(int x,int y){
		g.drawLine(x, y, x, y);
	}
	
	//��ʼ���������
	public void initUI(){
		//FlowLayout f1 = new FlowLayout();
		this.setTitle("ALOHA");
		this.setLayout(new FlowLayout());
		this.setSize(580,460);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.g = this.getGraphics();
	}
}