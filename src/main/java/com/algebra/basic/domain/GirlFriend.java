package com.algebra.basic.domain;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import lombok.Data;

import java.util.*;

/**
 * @author al
 * @date 2020/1/16 15:38
 * @description
 */
@Data
public class GirlFriend {

    private String name;
    private int age;
    private int bust;
    private int waist;
    private int hips;
    private List<String> hobby;
    private String birthday;
    private String address;
    private String mobile;
    private String email;
    private String hairColor;
    private Map<String, String> gift;

    // 为了演示方便，加几个聚合方法
    public void addHobby(String hobby) {
        this.hobby = Optional.ofNullable(this.hobby).orElse(new ArrayList<>());
        this.hobby.add(hobby);
    }
    public void addGift(String day, String gift) {
        this.gift = Optional.ofNullable(this.gift).orElse(new HashMap<>());
        this.gift.put(day, gift);
    }
    public void setVitalStatistics(int bust, int waist, int hips) {
        this.bust = bust;
        this.waist = waist;
        this.hips = hips;
    }

    public static int compareGirlFriendByAge(GirlFriend g1, GirlFriend g2){
        return g1.getAge() - g2.getAge();
    }

    public static void main(String[] args) {
        GirlFriend myGirlFriend = Builder.of(GirlFriend::new)
                .with(GirlFriend::setName, "小美")
                .with(GirlFriend::setAge, 18)
                .with(GirlFriend::setVitalStatistics, 33, 23, 33)
                .with(GirlFriend::setBirthday, "2001-10-26")
                .with(GirlFriend::setAddress, "上海浦东")
                .with(GirlFriend::setMobile, "18688888888")
                .with(GirlFriend::setEmail, "pretty-xiaomei@qq.com")
                .with(GirlFriend::setHairColor, "浅棕色带点微卷")
                .with(GirlFriend::addHobby, "逛街")
                .with(GirlFriend::addHobby, "购物")
                .with(GirlFriend::addHobby, "买东西")
                .with(GirlFriend::addGift, "情人节礼物", "LBR 1912女王时代")
                .with(GirlFriend::addGift, "生日礼物", "迪奥烈焰蓝金")
                .with(GirlFriend::addGift, "纪念日礼物", "阿玛尼红管唇釉")
                // 等等等等 ...
                .build();

        System.out.println(myGirlFriend.toString());

        List<GirlFriend> girlFriends = new ArrayList<>();
        girlFriends.add(myGirlFriend);

        // 四种方法引用及其返回值
        // 1.对象::实例方法名
        Supplier<String> getAddress = myGirlFriend::getAddress;
        // 2.类名::实例方法名
        Function<GirlFriend, String> getAddress1 = GirlFriend::getAddress;
        // 3.类名::静态方法名
        Comparator<GirlFriend> compareGirlFriendByAge = GirlFriend::compareGirlFriendByAge;

        girlFriends.sort((o1, o2) -> GirlFriend.compareGirlFriendByAge(o1,o2));
        girlFriends.sort(GirlFriend::compareGirlFriendByAge);
        girlFriends.forEach(s -> System.out.println(s.getAge()));
        // 4.类名::new
        Supplier<GirlFriend> girlFriendSupplier = GirlFriend::new;

    }

}
