package com.ysy.exam.board;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("== 게시판 v 0.1 =");
        System.out.println("== 프로그램 시작 =");

        int articlesLastId = 0;


        ArrayList<Article> articleList = new ArrayList<Article>();

        createTestData(articleList);

        if (articleList.size() > 0) {
            articlesLastId = articleList.get(articleList.size() - 1).id;
        }

        while (true) {
            System.out.printf("명령)");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);
            Map<String, String> params = rq.getParams();

            if (rq.getUrlPath().equals("exit")) {
                break;
            } else if (cmd.equals("/user/article/write")) {
                System.out.println("== 게시물 등록 ==");

                System.out.printf("제목: ");
                String title = sc.nextLine();

                System.out.printf("내용: ");
                String content = sc.nextLine();

                int id = articlesLastId + 1;
                articlesLastId++;

                Article article = new Article(id, title, content);

                articleList.add(article);

                System.out.println("생성된 게시물 객체: " + article);
                System.out.println(article.id + "번 게시물이 등록되었습니다.");

            } else if (rq.getUrlPath().equals("/user/article/detail")) {

                if (params.containsKey("id") == false) {
                    System.out.println("id값을 입력해주세요");
                    continue;
                }


                if (articleList.isEmpty()) {
                    System.out.println("게시글이 존재하지 않습니다");
                    continue;
                }

                int id = 0;

                try {
                    id = Integer.parseInt(params.get("id"));
                } catch (NumberFormatException e) {
                    System.out.println("id를 정수형태로 입력 해 주세요");
                    continue;
                }

                Article article = articleList.get(id - 1);

                if (id > articleList.size()) {
                    System.out.println("게시물이 존재하지 않습니다");
                    continue;
                }

                System.out.println("== 게시물 상세보기 ==");
                System.out.println(article);

            } else if (rq.getUrlPath().equals("/user/article/list")) {
                System.out.println("== 게시글 목록 ==");
                System.out.println("===============");
                System.out.println("== 번호 / 제목 / 내용 ==");
                System.out.println("===============");

                List<Article> sortedArticles = articleList;

                boolean orderByIdDesc = true;


                if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
                    orderByIdDesc = false;
                }

                if(orderByIdDesc){
                    sortedArticles = Util.reverseList(sortedArticles);
                }

                if (orderByIdDesc) {
                    for (int i = articleList.size() - 1; i >= 0; i--) {
                        Article article = articleList.get(i);
                        System.out.println("번호 - " + article.id + "제목 - " + article.title + "내용 - " + article.content);
                    }
                } else {
                    for (Article article : articleList) {
                        System.out.println("번호 - " + article.id + "제목 - " + article.title + "내용 - " + article.content);
                    }
                }
            } else {
                System.out.println("입력된 명령어 : " + cmd);
            }
        }
        System.out.println("== 프로그램 종료 ==");
        sc.close();
    }

    static void createTestData(ArrayList<Article> articleList) {
        articleList.add(new Article(1, "제목1", "내용1"));
        articleList.add(new Article(2, "제목2", "내용2"));
        articleList.add(new Article(3, "제목3", "내용3"));
        articleList.add(new Article(4, "제목4", "내용4"));
    }
}

class Rq {
    String url;

    Rq(String url) {
        this.url = url;
    }

    Map<String, String> getParams() {
        return Util.getParamsFromUrl(url);
    }

    String getUrlPath() {
        return Util.getUrlPathFromUrl(url);
    }
}

class Util {

    public static<T> List<T> reverseList(List<T> list){
        List<T> reverse = new ArrayList<T>(list.size());

        for(int i = list.size()-1; i>=0; i--){
            reverse.add(list.get(i));
        }
        return reverse;
    }

    static Map<String, String> getParamsFromUrl(String url) {
        Map<String, String> params = new HashMap<String, String>();
        String[] urlBits = url.split("\\?", 2);

        if (urlBits.length == 1) {
            return params;
        }

        for (String bit : urlBits[1].split("&")) {
            String[] bitBits = bit.split("=", 2);
            if (bitBits.length == 1) {
                continue;
            }
            params.put(bitBits[0], bitBits[1]);
        }

        return params;
    }

    static String getUrlPathFromUrl(String url) {
        return url.split("\\?", 2)[0];
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

    @Override
    public String toString() {
        return String.format("글 번호 - %d, 글 제목 - %s, 글 내용 - %s", id, title, content);
    }
}
