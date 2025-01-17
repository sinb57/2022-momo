package com.woowacourse.momo.acceptance.participant;

import static com.woowacourse.momo.acceptance.participant.ParticipantRestHandler.모임에_참여한다;
import static com.woowacourse.momo.acceptance.participant.ParticipantRestHandler.참여목록을_조회한다;
import static com.woowacourse.momo.fixture.MemberFixture.GUGU;
import static com.woowacourse.momo.fixture.MemberFixture.MOMO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.woowacourse.momo.acceptance.AcceptanceTest;
import com.woowacourse.momo.fixture.GroupFixture;
import com.woowacourse.momo.fixture.MemberFixture;

class ParticipantAcceptanceTest extends AcceptanceTest {

    private static final MemberFixture HOST = MemberFixture.DUDU;
    private static final GroupFixture GROUP = GroupFixture.DUDU_COFFEE_TIME;

    private String hostAccessToken;
    private Long groupId;

    @BeforeEach
    void setUp() {
        hostAccessToken = HOST.로_로그인한다();
        groupId = GROUP.을_생성한다(hostAccessToken);
    }

    @DisplayName("회원이 모임에 참여한다")
    @Test
    void participateByMember() {
        String accessToken = MOMO.로_로그인한다();
        모임에_참여한다(accessToken, groupId).statusCode(HttpStatus.OK.value());

        참여목록을_조회한다(groupId);
    }

    @DisplayName("주최자가 자신이 주최한 모임에 참여한다")
    @Test
    void participateByHost() {
        모임에_참여한다(hostAccessToken, groupId).statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("참여자가 자신이 참여한 모임에 재참여한다")
    @Test
    void participateByParticipants() {
        String accessToken = MOMO.로_로그인한다();
        모임에_참여한다(accessToken, groupId).statusCode(HttpStatus.OK.value());
        모임에_참여한다(accessToken, groupId).statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("존재하지 않은 모임에 참여한다")
    @Test
    void participateToNonExistentGroup() {
        모임에_참여한다(hostAccessToken, 0L).statusCode(HttpStatus.BAD_REQUEST.value()); // TODO: NOT_FOUND
    }

    @DisplayName("비회원이 모임에 참여한다")
    @Test
    void participateByNonMember() {
        모임에_참여한다(groupId).statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @DisplayName("정원이 가득 찬 모임에 참여한다")
    @Test
    void participateFullGroup() {
        String accessToken = MOMO.로_로그인한다();
        모임에_참여한다(accessToken, groupId).statusCode(HttpStatus.OK.value());

        accessToken = GUGU.로_로그인한다();
        모임에_참여한다(accessToken, groupId).statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("모임의 참여자 목록을 조회한다")
    @Test
    void findParticipants() {
        String accessToken = MOMO.로_로그인한다();
        모임에_참여한다(accessToken, groupId).statusCode(HttpStatus.OK.value());

        참여목록을_조회한다(groupId).statusCode(HttpStatus.OK.value());
    }
}
