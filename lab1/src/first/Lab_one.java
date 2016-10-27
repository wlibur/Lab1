package first;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Lab_one {
	
	
	private static xiao;
	private static wlibur;

	//���ڱ����������еĶ���ʽ
	private static String exp="";   
	//������ʽ�������ж�����Ķ���ʽ�Ƿ�Ϸ�
	private static String pattern="^(((-?([a-zA-Z]+)\\s*(\\^\\s*([0-9]+))?)|(((-?\\d+(\\.\\d+)?)\\s*)(([a-zA-Z]+)(\\^\\s*([0-9]+))?)?\\s*))[\\+\\-\\*]\\s*)*((-?([a-zA-Z]+)\\s*(\\^\\s*([0-9]+))?)|(((-?\\d+(\\.\\d+)?)\\s*)(([a-zA-Z]+)(\\^\\s*([0-9]+))?)?\\s*))$";

	//������ʽ�����ڰ�����ָ����ʽ���ԼӺ�Ϊ��׼���зָ
	private static String pattern2="(\\+|-)(([A-Za-z]+|(-?(([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)[a-zA-Z]*|\\d+[a-zA-Z]*)))(\\*|\\^))*([A-Za-z]+|(-?(([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)|\\d+[a-zA-Z]*)))";
    //Pattern�����ڴ���һ��������ʽ//r���ô���ʲô??
	private static Pattern r=null;
    //�ַ������飬���ڴ洢�ָ��Ķ���ʽ    ��listΪȫ�ֱ���
	private static ArrayList<String> list =new ArrayList<String>();
    
	
	
	
	//���Ӻų����ָ�ĺ��� 
    private static void expression(String text){
    	list.clear();
    	Pattern r2 = Pattern.compile(pattern2);
    	Matcher m2 = r2.matcher(text);
    	while(m2.find()){
    		list.add(m2.group(0));
    	}
    	for(int i=0;i<list.size();i++){
    		//�жϷָ����������Ƿ����û�г˺ŵĳ˷�
    		list.set(i, list.get(i).replaceAll("(\\d+\\.?\\d*)([a-zA-Z]+)", "$1\\*$2"));
    		//System.out.println(list.get(i));
    	}
    	//���list������Ԫ�ؿ�ͷ����0����ô�򽫸�Ԫ�ظ�ֵΪ0
    	
    	
    	
    	//simplifyHelper();
    }
    //�������ݼ������ʽ
    private static void simplifyHelper(){
    	exp="";
    	String exp1="";
    	float exp2=0;
    	for(int i=0;i<list.size();i++){
    		//System.out.println(list.get(i));
    		String[] s=list.get(i).substring(1).split("\\*");
    		String s2="";
    		float n =1;
    		for(int j=0;j<s.length;j++){
    			if(s[j].matches("-?\\d+\\.?\\d*")){
    				n=n*Float.parseFloat(s[j]);
    			}
    			else{
    				s2=s2+s[j]+"*";
    			}
    		}
    		//list.set(i, list.get(i).charAt(0)+(n==1?(s2.length()>0?"":"1"):((n-(int)n)==0?Integer.toString((int)n):Float.toString(n)))+(s2.length()>0?(n!=1?"*"+s2.substring(0, s2.length()-1):s2.substring(0, s2.length()-1)):""));
    		//System.out.println(n);
    		if(n==0)
    		{
    			list.set(i,list.get(i).charAt(0)+"0");
    			
    		}
    		else
    		{
    			list.set(i, list.get(i).charAt(0)+(n==1?(s2.length()>0?"":"1"):((n-(int)n)==0?Integer.toString((int)n):Float.toString(n)))+(s2.length()>0?(n!=1?"*"+s2.substring(0, s2.length()-1):s2.substring(0, s2.length()-1)):""));
    			
    		}
    		
    		
    		
    		list.set(i, list.get(i).replaceAll("\\+\\-", "\\-"));
    		list.set(i, list.get(i).replaceAll("\\-\\-", "\\+"));
    		/////System.out.println(list.get(i));
    		if(list.get(i).matches("(\\+|-)?\\d+\\.?\\d*")){
    				exp2+=Float.parseFloat(list.get(i));
    		}
    		else {
				exp1+=list.get(i);
			}
    		
    		
    	}
    	exp=exp1+(exp2>0?"+":"")+(exp2==0?"":((exp2-(int)exp2)==0?Integer.toString((int)exp2):Float.toString(exp2)));
    	//System.out.println(exp);
    	expression(exp);
    }
    
    private static boolean simplify(String[] command){
    	String temp=exp;
    	
    	//System.out.println(command[1]);
    	
    	for(int i=1;i<command.length;i++){
    		//����:��ԭʽ�еı���ȫ���滻Ϊ��Ӧ������
    		
    		if(command[i].matches("[a-zA-Z]+=-?(([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)|\\d+)")){
    			String var=command[i].substring(0, command[i].indexOf("="));
    			String value=command[i].substring(command[i].indexOf("=")+1);
    			temp+="+";
    			while(!temp.equals(temp.replaceAll("([\\+\\-\\*])"+var+"([\\+\\-\\*])", "$1"+value+"$2"))){
        			temp=temp.replaceAll("([\\+\\-\\*])"+var+"([\\+\\-\\*])", "$1"+value+"$2");
    			}
    			temp=temp.substring(0, temp.length()-1);
    		}
    		else{
    			return false;
    		}
    	}
    	expression(temp);
    	simplifyHelper();
    	return true;
    }
    
    private static boolean derivative(String var){
    	boolean result=false;
    	for(int i=0;i<list.size();i++){
    		String[] s=list.get(i).substring(1).split("\\*");
    		String s2="";
    		int n=0;
    		for(int j=0;j<s.length;j++){
    			if(s[j].equals(var)){
    				if(result==false){
    					result=true;
    				}
    				n++;
    				if(n>1){
    					s2=s2+var+"*";
    				}
    			}
    			else{
    				s2=s2+s[j]+"*";
    			}
    		}
    			list.set(i, list.get(i).charAt(0)+s2+n);
    	}
    	simplifyHelper();
    	return result;
    }
    //������
    public static void main(String[] args) {
    String may="";
    Scanner scanner = new Scanner(System.in);
    while(true){
    	String text=scanner.nextLine();
        if(text.matches("\\s*")){
        	break;
        }
        //�����ж������ʽ���Ƕ���ʽ����������Ҫ��
        if(text.charAt(0)=='!'){
        	exp=may;
        	expression(may);
        	if(text.matches("!d/d *[a-zA-Z]+")){
        		if(derivative(text.substring(4).trim())){
        			System.out.println(exp.charAt(0)=='+'?exp.substring(1):exp);
        		}
        		else{
        			System.out.println("Variable Not Exists!");
        		}
        		
        	}
        	else{
        		String[] command=text.split("\\s+");
            	
            	if(command.length>1 && command[0].equals("!simplify")){
            		exp=may;
                	expression(may);
            		if(simplify(command)){
                    	System.out.println(exp.equals("")?0:exp.charAt(0)=='+'?exp.substring(1):exp);	
            		}
            		else{
                		System.out.println("Command Error!");
                	}
            	}
            	else{
            		System.out.println("Command Error!");
            	}
        	}
        	
        }
        else{
        	r = Pattern.compile(pattern);
            Matcher m = r.matcher(text);
            if(m.find()){
            	String pattern4="\\^[a-zA-Z]+";
            	Pattern r4=Pattern.compile(pattern4);
            	Matcher m4=r4.matcher(text);
            	if(!m4.find()){
            		text=text.replaceAll("\\s+", "");
                	text=text.replaceAll("\\+\\-", "\\-");
                	text=text.replaceAll("\\-\\-", "\\+");
                	//text=text.replaceAll("([a-zA-Z]+|\\d+)\\^(\\d+)",addStrings("$1*","3")+"$1");
                	String pattern3="([a-zA-Z]+|\\d+)\\^\\d+";
                	Pattern r3=Pattern.compile(pattern3);
                	Matcher m3=r3.matcher(text);
                	while(m3.find()){
                		String tmpStr = "";
                		String[] var=m3.group(0).split("\\^");
                    	for(int i=0;i<Integer.parseInt(var[1])-1;i++){
                    		tmpStr += (var[0]+"*");
                    	}
                		text=text.replace(m3.group(0), tmpStr+var[0]);
                	}
                	if(text.charAt(0)!='-'){
                		text="+"+text;
                	}
                	//System.out.println(text);
                	expression(text);
                	simplifyHelper();
                	may=exp;
                	System.out.println(exp.charAt(0)=='+'?exp.substring(1):exp);
            	}
            	else{
            		System.out.println("Expression Error!");
            	}
            	
            }
            else {
    			System.out.println("Expression Error!");
    		}
        }
    }
    scanner.close();
  }
}