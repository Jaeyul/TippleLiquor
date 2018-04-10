package com.iot.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iot.test.vo.BoardVO;

@Mapper
public interface BoardMapper {

	@Select("select bNo, bName, bContent, bRegDate, uiNickName, bHit, bRecom, bCommentCount from bulletin_board order by bNo desc limit #{page} , 20")
	public List<BoardVO> boardList(@Param("page") int page);

	@Select("select bNo, bName, bContent, bRegDate, uiNickName, bHit, bRecom, bCommentCount from bulletin_board where bName = #{bv.bName} or bContent = #{bv.bContent} or uiNickName = #{bv.uiNickName}")
	public List<BoardVO> searchBoardList(@Param("bv") BoardVO bv, @Param("page") int page);

	@Select("select bNo, bName, bContent, bRegDate, uiNickName, bHit, bRecom, bCommentCount from bulletin_board where bNo = #{bNo}")
	public BoardVO selectByNo(@Param("bNo") Integer bNo);

	@Select("select count(1) from bulletin_board;")
	public Integer seletBoardCount();

	@Select("call p_board_insertGetNo(#{bv.bName}, #{bv.bContent}, #{bv.uiNickName})")
	public Integer insertBoard(@Param("bv") BoardVO bv);

	@Delete("delete from bulletin_board where bNo = #{bNo}")
	public int deleteBoard(@Param("bNo") Integer bNo);

	@Update("update bulletin_board set bName = #{bv.bName}, bContent = #{bv.bContent} where bNo = #{bv.bNo}")
	public int updateBoard(@Param("bv") BoardVO bv);

	@Update("update bulletin_board set bHit = bHit+1 where bNo = #{bNo}")
	public int updateBoardHit(@Param("bNo") int bNo);

	@Update("update bulletin_board set bRecom = bRecom+1 where bNo = #{bNo}")
	public int updateBoardRecom(@Param("bNo") int bNo);

	@Update("update bulletin_board set bCommentCount = bCommentCount+1 where bNo = #{bNo}")
	public int updateBoardCCP(@Param("bNo") int bNo);

	@Update("update bulletin_board set bCommentCount = bCommentCount-1 where bNo = #{bNo}")
	public int updateBoardCCM(@Param("bNo") int bNo);

}
