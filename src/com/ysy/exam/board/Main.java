package com.ysy.exam.board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("== 게시판 v 0.1 =");
        System.out.println("== 프로그램 시작 =");

        int articlesLastId = 0;

        while (true) {
            System.out.printf("명령)");
            String cmd = sc.nextLine();

            if (cmd.equals("exit")) {
                System.out.println("==프로그램 종료==");
                break;
            } else if (cmd.equals("/user/article/write")) {
                System.out.println("== 게시물 등록 ==");

                System.out.printf("제목: ");
                String title = sc.nextLine();

                System.out.printf("내용: ");
                String content = sc.nextLine();

                int id = articlesLastId + 1;
                articlesLastId++;

                Article article = new Article();
                article.id = id;
                article.title = title;
                article.content = content;


                System.out.println("생성된 게시물 객체: " + article );
                System.out.println(article.id + "번 게시물이 등록되었습니다.");
            }

           else {
                System.out.println("입력된 명령어 : " + cmd);
            }
        }
        sc.close();
    }
}

class Article{
    int id;
    String title;
    String content;

    public String toString(){
        return String.format("글 번호 - %d\n, 글 제목 - %s\n, 글 내용 - %s", id, title, content);
    }
}
