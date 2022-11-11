package com.ysy.exam.board;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int articleLastId = 0;
        Article lastArticle = null;

        System.out.println("== 게시판 v 0.1 ==");
        System.out.println("== 프로그램 시작 ==");
        while (true) {

            System.out.print("명령)");
            String cmd = sc.nextLine();

            if (cmd.equals("exit")) {
                break;

            } else if (cmd.equals("/usr/article/detail")) {
                Article article = lastArticle;
                if (lastArticle == null){
                    System.out.println("입력된 게시글이 없습니다.");
                    continue;
                }

                System.out.println("== 게시글 상세 보기 ==");
                System.out.println(lastArticle);


            } else if (cmd.equals("/usr/article/write")) {

                System.out.println("== 게시글 작성 ==");
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String content = sc.nextLine();

                int id = articleLastId + 1;
                System.out.println(id + "번째 게시글이 추가되었습니다.");
                articleLastId++;

                Article article = new Article(id, title, content);
                lastArticle = article;

                System.out.println(article);

            } else {
                System.out.println("입력된 명령어) " + cmd);
            }
        }
        System.out.println("== 프로그램 종료 ==");
        sc.close();
    }
}

class Article{
    int id;
    String title;
    String content;

    Article(int id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

     public String toString(){
        return String.format("아이디 : %d, 제목 : %s, 내용 : %s", id, title, content);
    }
}

