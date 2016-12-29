package qing.yun.hui.common.test;

import java.util.ArrayList;
import java.util.List;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月30日下午4:30:04
 **/
public class MyTest {
	
	public static void main(String[]args){
		
		List<Teach> teachs=initTeachs(5);
		List<Student> students=initStudents(5);
		test(teachs, Teach.class);
		
	}
	
	public static List<Teach> initTeachs(int count){
		List<Teach> list=new ArrayList<Teach>();
		for(int i=0;i<count;i++){
			Teach s=new Teach(i, i%2==0?"张三"+i:"李四"+i);
			list.add(s);
		}
		return list;
	}
	
	public static List<Student> initStudents(int count){
		List<Student> list=new ArrayList<Student>();
		for(int i=0;i<count;i++){
			Student s=new Student(11+i, i%2==0?"男":"女");
			list.add(s);
		}
		return list;
	}
	
	public static <T> void test(List<T> list,Class<T> clz){
//		System.out.println("class====>"+clz.equals(Teach.class));
//		System.out.println("=======>"+clz.getSimpleName().equals(Teach.class.getSimpleName()));
		if(clz.equals(Teach.class)){
			for(T t:list){
				Teach teach=(Teach)t;
				System.out.println(teach.getId()+","+teach.getName());
			}
		}else{
			for(T t:list){
				Student student=(Student)t;
				System.out.println(student.getAge()+","+student.getSex());
			}
		}
	}
}
