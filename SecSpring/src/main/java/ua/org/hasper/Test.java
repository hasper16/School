package ua.org.hasper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.Mark;
import ua.org.hasper.Entity.Enums.MarkType;
import ua.org.hasper.Entity.Enums.UserRole;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.myTest.Translit;
import ua.org.hasper.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Pavel.Eremenko on 17.01.2017.
 */
public class Test {
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
            private ScheduleTimesService scheduleTimesService;
    @Autowired
            private ScheduleService scheduleService;
    @Autowired
            private JurnalService jurnalService;
    @Autowired
            private MarkStampService markStampService;

    Test (GroupService groupService,
          StudentService studentService,
          UserService userService,
          TeacherService teacherService,
          SubjectService subjectService,
          ScheduleTimesService scheduleTimesService,
          ScheduleService scheduleService,
          JurnalService jurnalService,
          MarkStampService markStampService){
        this.groupService=groupService;
        this.studentService=studentService;
        this.userService=userService;
        this.teacherService=teacherService;
        this.subjectService=subjectService;
        this.scheduleTimesService=scheduleTimesService;
        this.scheduleService=scheduleService;
        this.jurnalService=jurnalService;
        this.markStampService=markStampService;
    }
    public void mainTest(){
        addGroups();
        addUser("admin",UserRole.ADMIN);
        addStudents(10);
        addTeachers(10);
        addSubjects();
        addScheduleTimes();
        addSchedules(7);
        try {
            addJournals(3);
        } catch (ParseException e) {
            System.out.println("Parse date exception in Test Create Schedule ---- "+e.getLocalizedMessage());
        }
    }
    public  CustomUser addUser(String login, UserRole role){
        String newPasswordStr = "password";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder() ;
        String newPassword = bCryptPasswordEncoder.encode(newPasswordStr);
        CustomUser customUser=new CustomUser(login,newPassword,role);
        userService.addUser(customUser);
        return customUser;
    }
    public  void addGroups(){
        char[] groupChar = {'А','Б','В','Г'};
        for(int i=1;i<=12;i++){
            for (int j=0;j<groupChar.length;j++){
                String str=Integer.toString(i)+"-"+groupChar[j];
                groupService.addGroup(new StudentsGroup(str));
            }
        }
    }
    public  void addStudents (int count){

        for (int i = 0;i<count;i++){
            String name = generateName();
            String surname = generateSurname();
            String login = Translit.cyr2lat(name.substring(0, 1) + surname);

            int y = 1998 + (int) (Math.random() * 10);
            int m = 1 + (int) (Math.random() * 12);
            int d = 1 + (int) (Math.random() * 28);
            Calendar birthday = new GregorianCalendar(y, m, d);

            long phone = 670000000 + (int) (Math.random() * 9999999);
            String email = login +"@"+ generateEmail();
            CustomUser customUser = addUser(login, UserRole.STUDENT);
            StudentsGroup studentsGroup = generateGroup();

            Student student = new Student(name, surname, birthday, phone, email, customUser, studentsGroup);
            groupService.addGroup(studentsGroup);
            studentService.addStudent(student);

        }
    }
    public  void addTeachers(int count){
        for (int i = 0;i<count;i++){
            String name = generateName();
            String surname = generateSurname();
            String login = Translit.cyr2lat(name.substring(0, 1) + surname);

            int y = 1965 + (int) (Math.random() * 30);
            int m = 1 + (int) (Math.random() * 12);
            int d = 1 + (int) (Math.random() * 28);
            Calendar birthday = new GregorianCalendar(y, m, d);

            long phone = 670000000 + (int) (Math.random() * 9999999);
            String email = login +"@"+ generateEmail();
            CustomUser customUser = addUser(login, UserRole.TEACHER);

            Teacher teacher = new Teacher(name,surname,birthday,phone,email,customUser);
            teacherService.addOrUpdateTeacher(teacher);
        }
    }
    public  void addSubjects(){
        subjectService.addOrUpdateSubject(new Subject("Чистописание"));
        subjectService.addOrUpdateSubject(new Subject("Чтение"));
        subjectService.addOrUpdateSubject(new Subject("Труд"));
        subjectService.addOrUpdateSubject(new Subject("Природоведение"));
        subjectService.addOrUpdateSubject(new Subject("Математика"));
        subjectService.addOrUpdateSubject(new Subject("Музыка"));
        subjectService.addOrUpdateSubject(new Subject("Изобразительное искусство"));
        subjectService.addOrUpdateSubject(new Subject("Русский язык"));
        subjectService.addOrUpdateSubject(new Subject("Физкультура"));
        subjectService.addOrUpdateSubject(new Subject("Родной (национальный) язык"));
        subjectService.addOrUpdateSubject(new Subject("Основы религиозных культур и светской этики"));
        subjectService.addOrUpdateSubject(new Subject("Иностранный язык"));
        subjectService.addOrUpdateSubject(new Subject("Граждановедение"));
        subjectService.addOrUpdateSubject(new Subject("Краеведение"));
        subjectService.addOrUpdateSubject(new Subject("История"));
        subjectService.addOrUpdateSubject(new Subject("Литература"));
        subjectService.addOrUpdateSubject(new Subject("Основы безопасности жизнедеятельности (ОБЖ)"));
        subjectService.addOrUpdateSubject(new Subject("Технология"));
        subjectService.addOrUpdateSubject(new Subject("География"));
        subjectService.addOrUpdateSubject(new Subject("Биология"));
        subjectService.addOrUpdateSubject(new Subject("Информатика"));
        subjectService.addOrUpdateSubject(new Subject("Обществознание"));
        subjectService.addOrUpdateSubject(new Subject("Черчение"));
        subjectService.addOrUpdateSubject(new Subject("Алгебра"));
        subjectService.addOrUpdateSubject(new Subject("Геометрия"));
        subjectService.addOrUpdateSubject(new Subject("Физика"));
        subjectService.addOrUpdateSubject(new Subject("Химия"));
        subjectService.addOrUpdateSubject(new Subject("Естествознание"));
        subjectService.addOrUpdateSubject(new Subject("Основы экономики"));
        subjectService.addOrUpdateSubject(new Subject("Правоведение"));
        subjectService.addOrUpdateSubject(new Subject("Философия"));
        subjectService.addOrUpdateSubject(new Subject("Экология"));
        subjectService.addOrUpdateSubject(new Subject("Астрономия"));
        subjectService.addOrUpdateSubject(new Subject("Начальная военная подготовка (НВП)"));
    }
    public void addScheduleTimes(){
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(1, new GregorianCalendar(0,0,0,9,0),45));
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(2, new GregorianCalendar(0,0,0,10,0),45));
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(3, new GregorianCalendar(0,0,0,11,0),45));
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(4, new GregorianCalendar(0,0,0,12,0),45));
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(5, new GregorianCalendar(0,0,0,13,0),45));
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(6, new GregorianCalendar(0,0,0,14,0),45));
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(7, new GregorianCalendar(0,0,0,15,0),45));
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(8, new GregorianCalendar(0,0,0,16,0),45));
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(9, new GregorianCalendar(0,0,0,17,0),45));
        scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(10, new GregorianCalendar(0,0,0,18,0),45));
    }
    public void addSchedules(int countSubjectInDay){
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<StudentsGroup>studentsGroups = groupService.getAllGroups();
        List<ScheduleTimes>scheduleTimes = scheduleTimesService.getAll();
        WeekDayName.values();
        Random random = new Random();
        for(int i=1;i<WeekDayName.values().length;i++){
            for (StudentsGroup sg:
                 studentsGroups) {
                Subject subject = subjects.get(random.nextInt(subjects.size()));
                Teacher teacher = teachers.get(random.nextInt(teachers.size()));
                for (ScheduleTimes st:
                     scheduleTimes) {
                    if(st.getLessonNum()<=countSubjectInDay){
                        Schedule schedule = new Schedule(subject,teacher,sg,WeekDayName.values()[i],st);
                        scheduleService.addOrUpdateSchedule(schedule);
                    }
                }
            }


        }


    }
    public void addJournals(int countWeeks) throws ParseException {
        List<Schedule>schedules=scheduleService.findAll();
        for (Schedule s:
             schedules) {
            for (int i=0;i<countWeeks;i++){
                Subject subject = s.getSubject();
                Teacher teacher = s.getTeacher();
                StudentsGroup studentsGroup=s.getStudentsGroup();
                List<Student> students = studentsGroup.getStudents();
                for (Student st:
                     students) {
                    Random random = new Random();
                    Mark m =Mark.values()[random.nextInt(Mark.values().length)];
                    MarkType mt = null;
                    if (m.getNumMark()!=-1){
                        if(m.getNumMark()!=0){
                            mt=MarkType.THEMATIC;
                        }
                        else{
                            mt=MarkType.CONTROL;
                        }
                    }
                    else {
                        mt=MarkType.VISIT;
                    }
                    String sdt = s.getScheduleTimes().getSdt();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    Date date = dateFormat.parse(sdt);
                    int hh=date.getHours();
                    int mm=date.getMinutes();
                    Calendar createDate = new GregorianCalendar();
                    createDate.set(Calendar.HOUR,hh);
                    createDate.set(Calendar.MINUTE,mm);
                    createDate.set(Calendar.DAY_OF_MONTH,-7*i);
                    MarkStamp markStamp=new MarkStamp(m,mt,createDate);
                    Jurnal jurnal = new Jurnal(subject,studentsGroup,teacher,markStamp,st);
                    markStampService.addOrUpdateMarkStamp(markStamp);
                    jurnalService.addOrUpdateJurnal(jurnal);
                }
            }
        }
    }
    public  String generateName(){
        List<String> names = new LinkedList<>();

        names.add("Александр");
        names.add("Сергей");
        names.add("Владимир");
        names.add("Андрей");
        names.add("Алексей");
        names.add("Дмитрий");
        names.add("Николай");
        names.add("Евгений");
        names.add("Михаил");
        names.add("Юрий");
        names.add("Виктор");
        names.add("Иван");
        names.add("Игорь");
        names.add("Анатолий");
        names.add("Максим");
        names.add("Олег");
        names.add("Павел");
        names.add("Валерий");
        names.add("Константин");
        names.add("Вячеслав");
        names.add("Василий");
        names.add("Денис");
        names.add("Антон");
        names.add("Илья");
        names.add("Виталий");
        names.add("Роман");
        names.add("Никита");
        names.add("Леонид");
        names.add("Владислав");
        names.add("Геннадий");
        names.add("Артём");
        names.add("Кирилл");
        names.add("Тимофей");
        names.add("Егор");
        names.add("Лев");
        names.add("Матвей");
        names.add("Ярослав");
        names.add("Данил");
        names.add("Семён");
        names.add("Марк");
        names.add("Степан");
        names.add("Даниил");
        names.add("Арсений");
        names.add("Савелий");
        names.add("Елена");
        names.add("Татьяна");
        names.add("Ольга");
        names.add("Наталья");
        names.add("Ирина");
        names.add("Светлана");
        names.add("Анна");
        names.add("Екатерина");
        names.add("Людмила");
        names.add("Мария");
        names.add("Галина");
        names.add("Юлия");
        names.add("Анастасия");
        names.add("Валентина");
        names.add("Надежда");
        names.add("Марина");
        names.add("Нина");
        names.add("Любовь");
        names.add("Александра");
        names.add("Вера");
        names.add("Лариса");
        names.add("Оксана");
        names.add("Тамара");
        names.add("Дарья");
        names.add("Евгения");
        names.add("Ксения");
        names.add("Лидия");
        names.add("Наталия");
        names.add("Виктория");
        names.add("Елизавета");
        names.add("София");
        names.add("Алиса");
        names.add("Полина");
        names.add("Арина");
        names.add("Варвара");
        names.add("Софья");
        names.add("Валерия");
        names.add("Вероника");
        names.add("Ева");
        names.add("Милана");
        names.add("Василиса");
        names.add("Алёна");
        names.add("Таисия");
        names.add("Маргарита");
        names.add("Ульяна");
        names.add("Кристина");
        names.add("Кира");
        names.add("Есения");
        names.add("Диана");

        Random random = new Random();
        String res = names.get(random.nextInt(names.size()));

        return res;
    }
    public  String generateSurname(){
        List<String> surnames = new LinkedList<>();

        surnames.add("Абрамов");
        surnames.add("Авдеев");
        surnames.add("Агафонов");
        surnames.add("Аксёнов");
        surnames.add("Александров");
        surnames.add("Алексеев");
        surnames.add("Андреев");
        surnames.add("Анисимов");
        surnames.add("Антонов");
        surnames.add("Артемьев");
        surnames.add("Архипов");
        surnames.add("Афанасьев");
        surnames.add("Баранов");
        surnames.add("Белов");
        surnames.add("Белозёров");
        surnames.add("Белоусов");
        surnames.add("Беляев");
        surnames.add("Беляков");
        surnames.add("Беспалов");
        surnames.add("Бирюков");
        surnames.add("Блинов");
        surnames.add("Блохин");
        surnames.add("Бобров");
        surnames.add("Бобылёв");
        surnames.add("Богданов");
        surnames.add("Большаков");
        surnames.add("Борисов");
        surnames.add("Брагин");
        surnames.add("Буров");
        surnames.add("Быков");
        surnames.add("Васильев");
        surnames.add("Веселов");
        surnames.add("Виноградов");
        surnames.add("Вишняков");
        surnames.add("Владимиров");
        surnames.add("Власов");
        surnames.add("Волков");
        surnames.add("Воробьёв");
        surnames.add("Воронов");
        surnames.add("Воронцов");
        surnames.add("Гаврилов");
        surnames.add("Галкин");
        surnames.add("Герасимов");
        surnames.add("Голубев");
        surnames.add("Горбачёв");
        surnames.add("Горбунов");
        surnames.add("Гордеев");
        surnames.add("Горшков");
        surnames.add("Григорьев");
        surnames.add("Гришин");
        surnames.add("Громов");
        surnames.add("Гуляев");
        surnames.add("Гурьев");
        surnames.add("Гусев");
        surnames.add("Гущин");
        surnames.add("Давыдов");
        surnames.add("Данилов");
        surnames.add("Дементьев");
        surnames.add("Денисов");
        surnames.add("Дмитриев");
        surnames.add("Доронин");
        surnames.add("Дорофеев");
        surnames.add("Дроздов");
        surnames.add("Дьячков");
        surnames.add("Евдокимов");
        surnames.add("Евсеев");
        surnames.add("Егоров");
        surnames.add("Елисеев");
        surnames.add("Емельянов");
        surnames.add("Ермаков");
        surnames.add("Ершов");
        surnames.add("Ефимов");
        surnames.add("Ефремов");
        surnames.add("Жданов");
        surnames.add("Жуков");
        surnames.add("Журавлёв");
        surnames.add("Зайцев");
        surnames.add("Захаров");
        surnames.add("Зимин");
        surnames.add("Зиновьев");
        surnames.add("Зуев");
        surnames.add("Зыков");
        surnames.add("Иванков");
        surnames.add("Иванов");
        surnames.add("Игнатов");
        surnames.add("Игнатьев");
        surnames.add("Ильин");
        surnames.add("Исаев");
        surnames.add("Исаков");
        surnames.add("Кабанов");
        surnames.add("Казаков");
        surnames.add("Калашников");
        surnames.add("Калинин");
        surnames.add("Капустин");
        surnames.add("Карпов");
        surnames.add("Кириллов");
        surnames.add("Киселёв");
        surnames.add("Князев");
        surnames.add("Ковалёв");
        surnames.add("Козлов");
        surnames.add("Колесников");
        surnames.add("Колобов");
        surnames.add("Комаров");
        surnames.add("Комиссаров");
        surnames.add("Кондратьев");
        surnames.add("Коновалов");
        surnames.add("Кононов");
        surnames.add("Константинов");
        surnames.add("Копылов");
        surnames.add("Корнилов");
        surnames.add("Королёв");
        surnames.add("Костин");
        surnames.add("Котов");
        surnames.add("Кошелев");
        surnames.add("Красильников");
        surnames.add("Крылов");
        surnames.add("Крюков");
        surnames.add("Кудрявцев");
        surnames.add("Кудряшов");
        surnames.add("Кузнецов");
        surnames.add("Кузьмин");
        surnames.add("Кулагин");
        surnames.add("Кулаков");
        surnames.add("Куликов");
        surnames.add("Лаврентьев");
        surnames.add("Лазарев");
        surnames.add("Лапин");
        surnames.add("Ларионов");
        surnames.add("Лебедев");
        surnames.add("Лихачёв");
        surnames.add("Лобанов");
        surnames.add("Логинов");
        surnames.add("Лукин");
        surnames.add("Лыткин");
        surnames.add("Макаров");
        surnames.add("Максимов");
        surnames.add("Мамонтов");
        surnames.add("Марков");
        surnames.add("Мартынов");
        surnames.add("Маслов");
        surnames.add("Матвеев");
        surnames.add("Медведев");
        surnames.add("Мельников");
        surnames.add("Меркушев");
        surnames.add("Миронов");
        surnames.add("Михайлов");
        surnames.add("Михеев");
        surnames.add("Мишин");
        surnames.add("Моисеев");
        surnames.add("Молчанов");
        surnames.add("Морозов");
        surnames.add("Муравьёв");
        surnames.add("Мухин");
        surnames.add("Мышкин");
        surnames.add("Мясников");
        surnames.add("Назаров");
        surnames.add("Наумов");
        surnames.add("Некрасов");
        surnames.add("Нестеров");
        surnames.add("Никитин");
        surnames.add("Никифоров");
        surnames.add("Николаев");
        surnames.add("Никонов");
        surnames.add("Новиков");
        surnames.add("Носков");
        surnames.add("Носов");
        surnames.add("Овчинников");
        surnames.add("Одинцов");
        surnames.add("Орехов");
        surnames.add("Орлов");
        surnames.add("Осипов");
        surnames.add("Павлов");
        surnames.add("Панов");
        surnames.add("Панфилов");
        surnames.add("Пахомов");
        surnames.add("Пестов");
        surnames.add("Петров");
        surnames.add("Петухов");
        surnames.add("Поляков");
        surnames.add("Пономарёв");
        surnames.add("Попов");
        surnames.add("Потапов");
        surnames.add("Прохоров");
        surnames.add("Рогов");
        surnames.add("Родионов");
        surnames.add("Рожков");
        surnames.add("Романов");
        surnames.add("Русаков");
        surnames.add("Рыбаков");
        surnames.add("Рябов");
        surnames.add("Савельев");
        surnames.add("Савин");
        surnames.add("Сазонов");
        surnames.add("Самойлов");
        surnames.add("Самсонов");
        surnames.add("Сафонов");
        surnames.add("Селезнёв");
        surnames.add("Селиверстов");
        surnames.add("Семёнов");
        surnames.add("Сергеев");
        surnames.add("Сидоров");
        surnames.add("Силин");
        surnames.add("Симонов");
        surnames.add("Ситников");
        surnames.add("Соболев");
        surnames.add("Соколов");
        surnames.add("Соловьёв");
        surnames.add("Сорокин");
        surnames.add("Степанов");
        surnames.add("Стрелков");
        surnames.add("Субботин");
        surnames.add("Суворов");
        surnames.add("Суханов");
        surnames.add("Сысоев");
        surnames.add("Тарасов");
        surnames.add("Терентьев");
        surnames.add("Тетерин");
        surnames.add("Тимофеев");
        surnames.add("Титов");
        surnames.add("Тихонов");
        surnames.add("Третьяков");
        surnames.add("Трофимов");
        surnames.add("Туров");
        surnames.add("Уваров");
        surnames.add("Устинов");
        surnames.add("Фадеев");
        surnames.add("Фёдоров");
        surnames.add("Федосеев");
        surnames.add("Федотов");
        surnames.add("Филатов");
        surnames.add("Филиппов");
        surnames.add("Фокин");
        surnames.add("Фомин");
        surnames.add("Фомичёв");
        surnames.add("Фролов");
        surnames.add("Харитонов");
        surnames.add("Хохлов");
        surnames.add("Цветков");
        surnames.add("Чернов");
        surnames.add("Шарапов");
        surnames.add("Шаров");
        surnames.add("Шашков");
        surnames.add("Шестаков");
        surnames.add("Шилов");
        surnames.add("Ширяев");
        surnames.add("Шубин");
        surnames.add("Щербаков");
        surnames.add("Щукин");
        surnames.add("Юдин");
        surnames.add("Яковлев");
        surnames.add("Якушев");
        surnames.add("Смирнов");

        Random random = new Random();
        String res =surnames.get(random.nextInt(surnames.size()));
        return res;
    }
    public  String generateEmail(){
        List<String> prefEmail = new LinkedList<>();
        prefEmail.add("gmail.com");
        prefEmail.add("ukr.net");
        prefEmail.add("i.ua");
        prefEmail.add("mail.ru");
        prefEmail.add("yandex.ru");
        prefEmail.add("rambler.ru");
        prefEmail.add("yahooo.com");
        prefEmail.add("mail.com");

        Random random = new Random();
        return prefEmail.get(random.nextInt(prefEmail.size()));

    }
    public  StudentsGroup generateGroup( ){
        List<StudentsGroup>studentsGroups = groupService.getAllGroups();
        Random random = new Random();
        StudentsGroup studentsGroup = studentsGroups.get(random.nextInt(studentsGroups.size()));
        return studentsGroup;

    }
}
