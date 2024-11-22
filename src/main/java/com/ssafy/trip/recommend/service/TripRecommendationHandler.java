package com.ssafy.trip.recommend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.recommend.dto.request.TravelRouteRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class TripRecommendationHandler extends TextWebSocketHandler {
    private final OpenAiService openAiService;
    @Value("${open.ai.model}")
    private String model;


    public TripRecommendationHandler(@Value("${open.ai.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(60));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TravelRouteRequest request = mapper.readValue(message.getPayload(), TravelRouteRequest.class);

        String prompt = createPrompt(request.getSpots());

        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model(model)
                .messages(Arrays.asList(new ChatMessage("user", prompt)))
                .build();

        String response = openAiService.createChatCompletion(completionRequest)
                .getChoices().get(0).getMessage().getContent();

        session.sendMessage(new TextMessage(response));
    }

    private String createPrompt(List<String> spots) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("안녕하세요! 저는 여행 계획을 돕는 여행 가이드입니다. ");
        promptBuilder.append("다음 여행지들을 방문할 예정인데, 각 장소 주변의 숨은 명소나 맛집을 추천해주세요.\n\n");

        for (int i = 0; i < spots.size(); i++) {
            promptBuilder.append((i + 1) + ". " + spots.get(i) + "\n");
        }

        promptBuilder.append("\n다음 사항들을 고려해서 추천해주시면 감사하겠습니다:\n");
        promptBuilder.append("- 각 여행지 주변 도보 20분 이내의 장소들을 위주로 추천해주세요\n");
        promptBuilder.append("- 관광지, 맛집, 카페 등 다양한 유형의 장소를 골고루 추천해주세요\n");
        promptBuilder.append("- 각 추천 장소마다 특별한 점이나 꼭 봐야 할 포인트를 짧게 설명해주세요\n");
        promptBuilder.append("- 현지인들이 자주 가는 숨은 명소도 추천해주시면 좋겠습니다\n\n");
        promptBuilder.append("친근하고 대화하듯이 설명해주세요!");

        return promptBuilder.toString();
    }
}
