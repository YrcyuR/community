package life.xiaoyang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions = new ArrayList<>();
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer size, Integer page) {
        if(page<1)
        {
            this.page=1;
        }
        else
        {
            this.page=page;
        }
        totalPage = totalCount%size==0? totalCount/size : totalCount/size+1;
        if(page>totalPage)
        {
            this.page=totalPage;
        }
        if(this.page==0)
        {
            this.page=1;
        }
        pages.add(this.page);
        for(int i =1;i<=3;++i)
        {
            if (this.page-i>0)
            {
                pages.add(0,this.page-i);
            }
            if(this.page+i<=totalPage)
            {
                pages.add(this.page+i);
            }
        }

        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        if (this.page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        if (this.page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }


    }
}
