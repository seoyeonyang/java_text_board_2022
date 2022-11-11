package com.ysy.exam.board;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int articleId = 0;

        System.out.println("== 게시판 v 0.1 ==");
        System.out.println("== 프로그램 시작 ==");
        while (true) {

            System.out.print("명령)");
            String cmd = sc.nextLine();

            if (cmd.equals("exit")) {
                break;
            } else if (cmd.equals("/usr/article/write")) {
                System.out.println("== 게시글 작성 ==");
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String content = sc.nextLine();

                int id = articleId + 1;
                System.out.println(id + "번째 게시글이 추가되었습니다.");
                articleId++;
            } else {
                System.out.println("입력된 명령어) " + cmd);
            }
        }
        System.out.println("== 프로그램 종료 ==");
    }
}