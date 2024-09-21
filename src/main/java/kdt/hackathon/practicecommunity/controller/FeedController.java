package kdt.hackathon.practicecommunity.controller;

import kdt.hackathon.practicecommunity.dto.FeedDto;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import kdt.hackathon.practicecommunity.repository.FeedRepository;
import kdt.hackathon.practicecommunity.service.FeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class FeedController {
    // 위로(front) 보낼때는 Model & 아래로(service||entity||console||..)보낼때는 DTO
    // <h1>{{username}}님, 반갑습니다.html -> model.addAttribute("username", "김강민");

    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private FeedService feedService;


    @GetMapping("/feeds/new") // C 뷰페이지
    public String newFeed() {
        return "feeds/new";
    }

    @PostMapping("/feeds/create") // C api
    public String createFeed(FeedDto DTO) {
        System.out.println(DTO.toString()); // logging 넘겨저 오는 값

        Feed newfeed = DTO.toEntity();
        System.out.println(newfeed); // logging : 성공, 엔티티 변환
        Feed savedfeed = feedRepository.save(newfeed);
        System.out.println(savedfeed.toString()); // logging : 성공 디비 저장
        return "redirect:/feeds/" + savedfeed.getId();
    }

    @GetMapping("/feeds/{id}") // R 단일 뷰
    public String showFeed(@PathVariable Long id, Model model) {
        log.info("조회된 피드 id =" + id);
        // Feed feed = feedRepository.findById(id); 이유: 찾는값이 없는 경우를 고려 X 못함

        // Optional<Feed> feed = feedRepository.findById(id); 따라서 이거 혹은 밑에로 사용
        // * Optional 은 참과 거짓을 둘다 내포 할 수 있고, 아래는 없으면 널을 반환하라고 명시했기에
        Feed feedOne = feedRepository.findById(id).orElse(null);
        model.addAttribute("feed", feedOne);

        return "feeds/show";
    }

    @GetMapping("/feeds") // R 전체 뷰 (페이징은 아직)
    public String index(Model model) {

        // List<Feed> feeds = feedRepository.findAll();
        // findAll 은 Iterable 을 반환하지만, 받는 타입은 List 이기에 불일치한다는 메시지가 표시된다

        // 솔루션 1. 다운캐스팅 2. 업캐스팅 [둘다 인터페이스] / 3. ArrayList 사용 [클래스]
        // List<Feed> SolutionOne = (List<Feed>)feedRepository.findAll();
        // Iterable<Feed> SolutionTwo = feedRepository.findAll();

        // Solution Three : findAll 메소드가(JPA)가 ArrayList 를 반환하도록 수정 -> 오버라이딩
        List<Feed> feeds = feedRepository.findAll(); // 정확하게 하려면, 받는 값을 ArrayList<Feed>로 해야하지만, 상위타입이라 OK
        model.addAttribute("feeds", feeds);
        return "feeds/index";
    }

    @GetMapping("/feeds/{id}/edit") // U  뷰 페이지
    public String editFeed(@PathVariable Long id, Model model) {
        Feed resultfeed = feedRepository.findById(id).orElse(null);
        model.addAttribute("feed", resultfeed);
        return "feeds/edit";
    }

//    @PostMapping("/feeds/update") // U api
//    public String updateFeed(FeedDto DTO) {
//
//        log.info(DTO.toString());
//
//        Feed updatefeed = DTO.toEntity();
//        log.info(updatefeed.toString());
//
//        Feed targetfeed = feedRepository.findById(updatefeed.getId()).orElse(null);
//
//        if (targetfeed != null) {
//            feedRepository.save(updatefeed);
//        }
//        return "redirect:/feeds/" + updatefeed.getId();
//    }

    @PostMapping("/feeds/update") // U api2
    public String updateFeed2(FeedDto DTO) {
        Long id = DTO.getId();
        Feed resultfeed = feedService.update(id, DTO);
        return "redirect:/feeds/" + resultfeed.getId();

    }

    @GetMapping("/feeds/{id}/delete")
    public String deleteFeed(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.info("삭제 요청이 들어온 id =" + id);
        if (feedRepository.existsById(id)) {
            feedRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "삭제되었습니다!");
        }
        // RedirectAttributes : 리다이렉트 페이지에서 사용할 일회성 데이터를 관리하는 객체
        //  이 객체의 .addFlashAttribute() 메서드로 리다이렉트된 페이지에서 사용할 데이터 등록 가능
        return "redirect:/feeds";

    }

}


