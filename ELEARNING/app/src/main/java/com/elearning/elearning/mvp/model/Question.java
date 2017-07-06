package com.elearning.elearning.mvp.model;

import com.elearning.elearning.network.API;
import com.elearning.elearning.network.APIConstant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinhQuan on 07/07/2017.
 */

public class Question {
    private int idQuestion;
    private String contentQuestion;
    private List<Answer> answerList;

    Question() {

    }

    public Question(int idQuestion, String contentQuestion, List<Answer> answerList) {
        this.idQuestion = idQuestion;
        this.contentQuestion = contentQuestion;
        this.answerList = answerList;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public String getContentQuestion() {
        return contentQuestion;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public void setContentQuestion(String contentQuestion) {
        this.contentQuestion = contentQuestion;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public static List<Question> parseListQuestion(JSONArray jsonArray) {
        try {
            List<Question> questionList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = (JSONObject) jsonArray.get(i);
                Question question = new Question();
                question.setIdQuestion(data.getInt(APIConstant.IDQUESTION));
                question.setContentQuestion(data.getString(APIConstant.CONTENTQUESTION));
                List<Answer> answerList = new ArrayList<>();
                JSONArray arrayAnswer = data.getJSONArray(APIConstant.LISTANSWER);
                if (arrayAnswer.length() == 0) {
                    Answer answer = new Answer();
                    answer.setIdAnswer(-1);
                    answer.setContentAnswer("Chưa có đáp án");
                    answerList.add(answer);
                }
                {
                    for (int j = 0; j < arrayAnswer.length(); j++) {
                        JSONObject dataAnswer = (JSONObject) arrayAnswer.get(j);
                        Answer answer = new Answer();
                        answer.setIdAnswer(dataAnswer.getInt(APIConstant.IDANSWER));
                        answer.setContentAnswer(dataAnswer.getString(APIConstant.CONTENTANSWER));
                        answerList.add(answer);
                    }
                }
                question.setAnswerList(answerList);
                questionList.add(question);
            }
            return questionList;
        } catch (Exception ex) {
            return null;
        }
    }

    public static class Answer {
        private int idAnswer;
        private String contentAnswer;

        public Answer() {

        }

        public Answer(int idAnswer, String contentAnswer) {
            this.idAnswer = idAnswer;
            this.contentAnswer = contentAnswer;
        }

        public int getIdAnswer() {
            return idAnswer;
        }

        public String getContentAnswer() {
            return contentAnswer;
        }

        public void setIdAnswer(int idAnswer) {
            this.idAnswer = idAnswer;
        }

        public void setContentAnswer(String contentAnswer) {
            this.contentAnswer = contentAnswer;
        }
    }
}

