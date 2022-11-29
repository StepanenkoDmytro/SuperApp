package com.example.MyTestSpring.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity //тепер наш клас є моделю, та відповідає за якусь табличку в БД
@Table(name = "posts") //якщо не задати назву таблиці таким чином, то назва таблиці буде вибрана автоматично
@Data //анотація ломбоку, яка за мене пише гетери, сеттери, перевизначає hash-code та equals, визначає toString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //при додаванні нового запису, допоможе генерувати нове значення (для Id наприклад)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_full_text")
    private String full_text;

    @Column(name = "post_small_text")
    private String small_text;

    @Column(name = "post_views")
    private int views;

    /*
    Щоб маніпулювати БД (видаляти, додавати, перезаписувати і т.д., нам потрібно створити інтерфейс (Repository)
     */

//    -----------------------Тимчасовий код-----------------------
    public Post(String title, String full_text, String small_text) {
        /*
        створив конструктор, щоб отримвати параметри запису поста, з переданими значеннями
        потім перепишу на функцію
         */
        this.title = title;
        this.full_text = full_text;
        this.small_text = small_text;
    }

    public Post() {
        /*
        до цього програма працювала без конструктора(тобто з дефолтним пустим конструктором
        тому треба знову їй дати таку можливість :D
         */
    }
//    -----------------------Тимчасовий код-----------------------
}
