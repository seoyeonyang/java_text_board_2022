package com.ysy.exam.board;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Map<String, String> params = new HashMap<String, String>();

        ArrayList<Article> articles = new ArrayList<Article>();
        createTestArticles(articles);

        int lastArticleId = 0;
        if (articles.size() > 0){
            lastArticleId = articles.get(articles.size()-1).id;
        }

        Scanner sc = new Scanner(System.in);
        Article lastArticle= null;

        System.out.println("== 게시판 v 0.1 ==");
        System.out.println("== 프로그램 시작 ==");
        while (true) {

            System.out.print("명령)");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            if (rq.getUrlPath().equals("exit")) {
                break;

            } else if (rq.getUrlPath().equals("/usr/article/list")) {
                System.out.println("== 게시글 목록 ==");

                if (articles.size() < 0){
                    System.out.println("입력된 게시글이 없습니다.");
                }

                for (int i = articles.size()-1; i >=0; i--) {
                    System.out.println(articles.get(i));
                }


            } else if (rq.getUrlPath().equals("/usr/article/detail")) {

                if (articles == null) {
                    System.out.println("입력된 게시글이 없습니다.");
                    continue;
                }

                System.out.println("== 게시글 상세 보기 ==");
                Article article = articles.get(articles.size()-1);
                System.out.println(article);


            } else if (cmd.equals("/usr/article/write")) {

                System.out.println("== 게시글 작성 ==");
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String content = sc.nextLine();

                int id = lastArticleId + 1;
                lastArticleId = id;
                System.out.println(id + "번째 게시글이 추가되었습니다.");


                Article article = new Article(id, title, content);
                lastArticle = article;
                articles.add(article);

                System.out.println(article);

            } else {
                System.out.println("입력된 명령어) " + cmd);
            }
        }
        System.out.println("== 프로그램 종료 ==");
        sc.close();
    }

    static void createTestArticles(ArrayList<Article> articles) {
        for (int i = 0; i < 100; i++) {
            int id = i+1;
            articles.add(new Article(id, "title" + id, "content" + id));
        }
    }
}

class Article {
    int id;
    String title;
    String content;

    Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String toString() {
        return String.format("아이디 : %d, 제목 : %s, 내용 : %s", id, title, content);
    }
}

class Rq{

    private String url;
    private String urlPath;
    private Map<String, String> params;


    Rq(String url){
        this.url = url;
        urlPath = Util.getUrlPathFromUrl(this.url);
        params = Util.getParamsFromUrl(this.url);

    }

    public Map<String,String> getParams(){
        return params;
    }

    public String getUrlPath(){
        return urlPath;
    }
}


class Util {
    static Map<String, String> getParamsFromUrl(String url) {
        Map<String, String> params = new HashMap<String, String>();
        String[] urlBits = url.split("\\?", 2);

        if (urlBits.length == 1) {
            return params;
        }

        String queryStr = urlBits[1];
        for (String bit : queryStr.split("&")) {
            String[] bits = bit.split("=", 2);
            if (bits.length == 1) {
                continue;
            }
            params.put(bits[0], bits[1]);
        }

        return params;
    }

    static String getUrlPathFromUrl(String url) {
        return url.split("\\?", 2)[0];
    }
}
