package tool.utils.word.spire;

import com.spire.pdf.FileFormat;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class SpireCommonPDFUtils {


    public static String pdfToWordConversion(String pdfFile) throws Exception {
        com.spire.pdf.PdfDocument  pdf = new com.spire.pdf.PdfDocument(pdfFile);
        String fileName = FilenameUtils.getFullPath(pdfFile) + FilenameUtils.getBaseName(pdfFile) + ".docx";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        pdf.saveToFile(fileName, FileFormat.DOCX);
        return fileName;
    }

}
