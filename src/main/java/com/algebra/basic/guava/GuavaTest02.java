package com.algebra.basic.guava;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.*;

/**
 * @author al
 * @date 2019/12/26 10:03
 * @description
 */
public class GuavaTest02 {

    // 1. Range 表示一个间隔或一个序列。它被用于获取一组数字/串在一个特定范围之内。
    public void testRange(){
        //create a range [a,b] = { x | a <= x <= b} 闭区间
        Range<Integer> range1 = Range.closed(0, 9);
        System.out.print("[0,9] : ");
        printRange(range1);
        System.out.println("5 is present: " + range1.contains(5));
        System.out.println("(1,2,3) is present: " + range1.containsAll(Ints.asList(1, 2, 3)));
        System.out.println("Lower Bound: " + range1.lowerEndpoint());
        System.out.println("Upper Bound: " + range1.upperEndpoint());

        //create a range (a,b) = { x | a < x < b} 开区间
        Range<Integer> range2 = Range.open(0, 9);
        System.out.print("(0,9) : ");
        printRange(range2);

        //create a range (a,b] = { x | a < x <= b} 前开后闭
        Range<Integer> range3 = Range.openClosed(0, 9);
        System.out.print("(0,9] : ");
        printRange(range3);

        //create a range [a,b) = { x | a <= x < b} 前闭后开
        Range<Integer> range4 = Range.closedOpen(0, 9);
        System.out.print("[0,9) : ");
        printRange(range4);

        //create an open ended range (9, infinity)  到无穷大
        Range<Integer> range5 = Range.greaterThan(9);
        System.out.println("(9,infinity) : ");
        System.out.println("Lower Bound: " + range5.lowerEndpoint());
        System.out.println("Upper Bound present: " + range5.hasUpperBound());

        Range<Integer> range6 = Range.closed(3,5);
        printRange(range6);
        //check a subrange [3,5] in [0,9]  包含关系
        System.out.println("[0,9] encloses [3,5]:" + range1.encloses(range6));

        Range<Integer> range7 = Range.closed(9, 20);
        printRange(range7);
        //check ranges to be connected 连续的
        System.out.println("[0,9] is connected [9,20]:" + range1.isConnected(range7));

        Range<Integer> range8 = Range.closed(5, 15);
        //intersection 交集
        printRange(range1.intersection(range8));
        //span 并集
        printRange(range1.span(range8));

    }

    // 2.扩展设置有重复的元素，并提供了各种实用的方法来处理这样的元素在集合中出现。
    public void testMultiset(){
        //create a multiset collection
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");
        //print the occurrence of an element
        System.out.println("Occurrence of 'b' : "+multiset.count("b"));
        //print the total size of the multiset
        System.out.println("Total Size : "+multiset.size());
        //get the distinct elements of the multiset as set
        Set<String> set = multiset.elementSet();
        //display the elements of the set
        System.out.println("Set [");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("]");
        //display all the elements of the multiset using iterator
        Iterator<String> iterator  = multiset.iterator();
        System.out.println("MultiSet [");
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("]");
        //display the distinct elements of the multiset with their occurrence count
        System.out.println("MultiSet [");
        for (Multiset.Entry<String> entry : multiset.entrySet())
        {
            System.out.println("Element: "+entry.getElement() +", Occurrence(s): " + entry.getCount());
        }
        System.out.println("]");

        //remove extra occurrences
        multiset.remove("b",2);
        //print the occurrence of an element
        System.out.println("Occurence of 'b' : "+multiset.count("b"));
    }

    // 3.多重映射接口扩展映射，使得其键一次可被映射到多个值。 values 是一个集合list
    public void testMultimap(){
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");

        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");

        List<String> lowerList = (List<String>)multimap.get("lower");
        System.out.println("Initial lower case list");
        System.out.println(lowerList.toString());
        lowerList.add("f");
        System.out.println("Modified lower case list");
        System.out.println(lowerList.toString());

        List<String> upperList = (List<String>)multimap.get("upper");
        System.out.println("Initial upper case list");
        System.out.println(upperList.toString());
        upperList.remove("D");
        System.out.println("Modified upper case list");
        System.out.println(upperList.toString());

        Map<String, Collection<String>> map = multimap.asMap();
        System.out.println("Multimap as a map");
        for (Map.Entry<String,  Collection<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Collection<String> value =  multimap.get("lower");
            System.out.println(key + ":" + value);
        }

        System.out.println("Keys of Multimap");
        Set<String> keys =  multimap.keySet();
        for(String key:keys){
            System.out.println(key);
        }

        System.out.println("Values of Multimap");
        Collection<String> values = multimap.values();
        System.out.println(values);
    }

    // 通过value值反向查找key值
    public void testBimap(){
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();

        empIDNameMap.put(101, "Mahesh");
        empIDNameMap.put(102, "Sohan");
        empIDNameMap.put(103, "Ramesh");

        //Emp Id of Employee "Mahesh"
        System.out.println(empIDNameMap.inverse().get("Mahesh"));
    }

    // Table 特殊的映射，其中两个键可以在组合的方式被指定为单个值。它类似于创建映射的映射
    public void testTable(){
        //Table<R,C,V> == Map<R,Map<C,V>>
        /*
         *  Company: IBM, Microsoft, TCS
         *  IBM 		-> {101:Mahesh, 102:Ramesh, 103:Suresh}
         *  Microsoft 	-> {101:Sohan, 102:Mohan, 103:Rohan }
         *  TCS 		-> {101:Ram, 102: Shyam, 103: Sunil }
         *
         * */
        //create a table
        Table<String, String, String> employeeTable = HashBasedTable.create();

        //initialize the table with employee details
        employeeTable.put("IBM", "101","Mahesh");
        employeeTable.put("IBM", "102","Ramesh");
        employeeTable.put("IBM", "103","Suresh");

        employeeTable.put("Microsoft", "111","Sohan");
        employeeTable.put("Microsoft", "112","Mohan");
        employeeTable.put("Microsoft", "113","Rohan");

        employeeTable.put("TCS", "121","Ram");
        employeeTable.put("TCS", "122","Shyam");
        employeeTable.put("TCS", "123","Sunil");

        //get Map corresponding to IBM
        Map<String, String> ibm = employeeTable.row("IBM");

        System.out.println("List of IBM Employee");
        ibm.forEach((k,v)->{
            System.out.println("Emp Id: "+k+", Name: "+v);
        });
        //get all the unique keys of the table
        Set<String> employers = employeeTable.rowKeySet();
        System.out.print("Employers: ");
        for(String employer: employers){
            System.out.print(employer + " ");
        }
        System.out.println();

        //get a Map corresponding to 102
        Map<String,String> employerMap =  employeeTable.column("102");
        for(Map.Entry<String, String> entry : employerMap.entrySet()){
            System.out.println("Employer: " + entry.getKey() + ", Name: " + entry.getValue());
        }

    }

    private void printRange(Range<Integer> range){
        System.out.print("[ ");
        for(int grade : ContiguousSet.create(range, DiscreteDomain.integers())) {
            System.out.print(grade +" ");
        }
        System.out.println("]");
    }



    public static void main(String[] args) {

        GuavaTest02 test02 = new GuavaTest02();
        test02.testTable();



    }

}
