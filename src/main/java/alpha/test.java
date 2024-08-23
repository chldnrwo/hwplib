package alpha;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.object.bodytext.control.ControlTable;
import kr.dogfoot.hwplib.object.bodytext.control.table.Cell;
import kr.dogfoot.hwplib.object.bodytext.control.table.Row;
import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.object.bodytext.paragraph.text.ParaText;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.writer.HWPWriter;

import java.io.File;

public class test {
    public static void main(String[] args) {
        try {
            // HWP 파일 경로 지정
            String filePath = "C:\\Users\\chldn\\Desktop\\클라우드 월간 운영보고서_7월(제주특별자치도청).hwp";

            // HWP 파일 읽기
            HWPFile hwpFile = HWPReader.fromFile(filePath);
            if (hwpFile != null) {
                System.out.println("HWP 파일을 성공적으로 읽었습니다.");

                // 문서의 모든 섹션 순회
                for (Section section : hwpFile.getBodyText().getSectionList()) {
                    // 섹션 내 모든 문단 순회
                    for (Paragraph paragraph : section.getParagraphs()) {
                        // 문단 내의 컨트롤 리스트가 null이 아닌지 확인
                        if (paragraph.getControlList() != null) {
                            // 모든 컨트롤 순회
                            for (Object control : paragraph.getControlList()) {
                                // 컨트롤이 표인지 확인
                                if (control instanceof ControlTable &&
                                		((ControlTable) control).getRowList().get(0).getCellList().size() > 3) {
                                    ControlTable table = (ControlTable) control;
                                    
                                    if(table.getRowList().get(0).getCellList().get(3).getParagraphList()
                                    		.getParagraph(0).getNormalString().equals("백업주기")) {
                                    // 표의 모든 행 순회
                                    	/*
	                                    for (Row row : table.getRowList()) {
	                                        // 각 행의 모든 셀 순회
	                                        for (Cell cell : row.getCellList()) {
	
	                                            // 첫 번째 문단에 텍스트 추가
	                                            Paragraph firstParagraph = cell.getParagraphList().getParagraph(0);
	                                            ParaText paraText = firstParagraph.getText();
	                                            if(paraText==null) {
	                                            	continue;
	                                            }
	                                            	System.out.println(paraText.getNormalString(0));
	                                        }
	                                    }
                                    	 */
                                    
                                    for (int rowIndex = 0; rowIndex < table.getRowList().size(); rowIndex++) {
                                        Row row = table.getRowList().get(rowIndex);
                                        // 각 행의 모든 셀 순회
                                        for (int cellIndex = 0; cellIndex < row.getCellList().size(); cellIndex++) {
                                            Cell cell = row.getCellList().get(cellIndex);

                                            // 첫 번째 문단에 텍스트 추가
                                            Paragraph firstParagraph = cell.getParagraphList().getParagraph(0);
                                            ParaText paraText = firstParagraph.getText();
                                            if (paraText == null) {
                                                continue;
                                            }

                                            // "백업주기"라는 텍스트가 있는지 확인
                                            //if (paraText.getNormalString(0).equals("백업주기")) {
                                            	System.out.print(paraText.getNormalString(0)+"						");
                                                System.out.println("행 인덱스: " + rowIndex + ", 셀 인덱스: " + cellIndex);
                                            //}

                                        }
                                    }
                                   }
                                }
                            }
                        }
                    }
                }

                // 수정된 파일 저장 경로 지정
                String writePath = "C:\\Users\\chldn\\Desktop\\수정된_파일.hwp";
                HWPWriter.toFile(hwpFile, writePath);
                System.out.println("수정된 파일이 성공적으로 저장되었습니다: " + writePath);

            } else {
                System.out.println("HWP 파일을 읽는 데 실패했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}