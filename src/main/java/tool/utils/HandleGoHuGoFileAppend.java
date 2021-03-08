package tool.utils;

import com.alibaba.fastjson.JSONObject;
import com.entity.HandleGoHuGo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HandleGoHuGoFileAppend {
    private final static CharSequence delimiter = "\r";
    private static final Logger logger = LoggerFactory.getLogger(HandleGoHuGoFileAppend.class);


    public static void run(String oldPath,String hugoPath){
        HandleGoHuGo hugo = new HandleGoHuGo();
        hugo.setDraft(false);
        hugo.setTitle("我的博客");
        hugo.setDescription("测试博客");
        hugo.setAuthor("zch");
        hugo.setDate(DateUtils.todayDate());
        hugo.setLastmod(DateUtils.todayDate());
        List<String> extensions = new ArrayList<>(1);
        extensions.add("md");
        try {
            String newPath = FileUtils.getTempDirectoryPath() + UUID.randomUUID().toString().substring(0, 7) + File.separator;
            File file = new File(newPath);
            FileFilter filter = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isFile()) {
                        return extensions.contains(FilenameUtils.getExtension(f.getName()));
                    } else {
                        if (".git".equals(f.getName())) {
                            return false;
                        }
                        if (".github".equals(f.getName())) {
                            return false;
                        }
                        if (".idea".equals(f.getName())) {
                            return false;
                        }
                        if ("zch_diary".equals(f.getName())) {
                            return false;
                        }
                        if (StringUtils.contains(f.getName(), ".")) {
                            return false;
                        }
                    }
                    return true;
                }
            };
            FileUtils.copyDirectory(new File(oldPath), file, filter, false);
            replace(file, hugoPath, hugo, file);
            FileUtils.deleteDirectory(file);
            System.out.println("end!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String oldPath = "E:\\studyDoc";
        String hugoPath = "D:\\doc\\blog\\quickstart\\content\\post";
        run(oldPath,hugoPath) ;
    }


    private static void replace(File file, String hugoPath, HandleGoHuGo base, File source) {
        if (file.isFile()) {
            try {
                String string = FileUtils.readFileToString(file, "UTF-8");
                HandleGoHuGo hugo = new HandleGoHuGo();
                if (CollectionUtils.isNotEmpty(base.getCategories())) {
                    base.getCategories().clear();
                }
                if (CollectionUtils.isNotEmpty(base.getTags())) {
                    base.getTags().clear();
                }
                BeanUtils.copyProperties(base, hugo);
                settingText(source, file, hugo);
                String headText = hugo.getvText();
                String newString = new StringJoiner(delimiter).add(headText).add(string).toString();
                FileUtils.write(file, newString, false);
                if (StringUtils.isNotBlank(hugoPath)) {
                    //file 不能是index和 README
                    String extension = FilenameUtils.getExtension(file.getName());
                    String baseName = FilenameUtils.getBaseName(file.getName());
                    boolean check = baseName.equalsIgnoreCase("index") || baseName.equalsIgnoreCase("README");
                    if (!check) {
                        LinkedHashSet<String> tags = hugo.getTags();
                        List<String> strings = new ArrayList<>(tags.size());
                        strings.add("uuid_t");
                        tags.forEach(s -> strings.add(s));
                        strings.add(baseName);
                        String path = String.format("%s%s%s%s%s", hugoPath, File.separator, StringUtils.join(strings, "_"), ".", extension);
                        List<String> stringList = FileUtils.readLines(file);
                        if (CollectionUtils.isNotEmpty(stringList)) {
                            stringList = stringList.stream().filter(s -> !StringUtils.contains(s, "回到上一级")).collect(Collectors.toList());
                            FileUtils.writeLines(new File(path), stringList, true);
                        }
                    }
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (File f : files) {
                replace(f, hugoPath, base, source);
            }
        }
    }

    private static void settingText(File source, File file, HandleGoHuGo hugo) {
        String sourcePath = source.getPath();
        File fileParent = null;
        if (!sourcePath.equalsIgnoreCase(file.getPath())) {
            do {
                fileParent = fileParent == null ? file.getParentFile() : fileParent;
                if (!fileParent.getName().equals(source.getName())) {
                    String baseName = FilenameUtils.getBaseName(fileParent.getName());
                    String[] split = baseName.split("_");
                    for (String s : split) {
                        hugo.getTags().add(s);
                    }
//                    hugo.getTags().add(fileParent.getName());
                }
                if (!fileParent.getParentFile().getName().equals(source.getName())) {
                    String baseName = FilenameUtils.getBaseName(fileParent.getParentFile().getName());
                    String[] split = baseName.split("_");
                    for (String s : split) {
                        hugo.getCategories().add(s);
                    }
//                    hugo.getCategories().add(fileParent.getParentFile().getName());
                }
                fileParent = fileParent.getParentFile();
            } while (sourcePath.equalsIgnoreCase(fileParent.getPath()));
        }
        hugo.getCategories().add("index");
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("---").append(delimiter);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>(8);
        String baseName = FilenameUtils.getBaseName(file.getName());
        String[] split = baseName.split("_");
        List<String> strings = Arrays.asList(split);
        map.put("title", StringUtils.join(strings, " > "));
        map.put("date", hugo.getDate());
        map.put("draft", hugo.isDraft());
        map.put("tags", JSONObject.toJSONString(hugo.getTags()));
        map.put("categories", JSONObject.toJSONString(hugo.getCategories()));
        map.put("author", hugo.getAuthor());
        map.put("description", hugo.getDescription());
        map.put("lastmod", hugo.getLastmod());
        map.forEach((s, o) -> {
            stringBuilder.append(s).append(" : ");
            if (!(o instanceof String)) {
                stringBuilder.append(o);
            } else if (StringUtils.contains(String.valueOf(o), "[")) {
                stringBuilder.append(o);
            } else {
                stringBuilder.append("'").append(o).append("'");
            }
            stringBuilder.append(delimiter);
        });
        stringBuilder.append("---").append(delimiter);
        hugo.setvText(stringBuilder.toString());
    }

}
