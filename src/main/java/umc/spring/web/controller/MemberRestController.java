package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.MissionAlreadyChallenged;
import umc.spring.web.dto.Member.MemberRequestDTO;
import umc.spring.web.dto.Member.MemberResponseDTO;
import umc.spring.web.dto.Mission.MissionRequestDTO;
import umc.spring.web.dto.Mission.MissionResponseDTO;
import umc.spring.web.dto.Review.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    
    @PostMapping("/")
    @Operation(summary = "회원가입 API",description = "회원가입 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    // 로그인 시 ReviewController 이동 예정(/api/reviews)
    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API",description = "내가 작성한 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, query string 입니다.")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistMember @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = memberQueryService.getReviewList(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewList));
    }


    // 로그인 시 MissionController로 이동 예정(/api/missions)
    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "내가 진행 중인 미션 목록을 조회하는 API 이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, query string 입니다.")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@ExistMember @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page) {
        Page<MemberMission> missionList = memberQueryService.getMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberMissionConverter.missionPreViewListDTO(missionList));
    }
}