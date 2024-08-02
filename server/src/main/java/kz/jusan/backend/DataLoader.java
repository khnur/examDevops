package kz.jusan.backend;

import com.github.javafaker.Faker;
import kz.jusan.backend.entity.*;
import kz.jusan.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDatabase(
            AnketaRepository anketaRepository,
            AttachmentRepository attachmentRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            UserProfileRepository userProfileRepository,
            PasswordEncoder passwordEncoder
    ) {
        attachmentRepository.deleteAll();
        anketaRepository.deleteAll();
        userProfileRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();

        return args -> {
            Faker faker = new Faker(new Locale("en"));

            IntStream.range(0, 5)
                    .mapToObj(i -> Attachment.builder()
                            .id(faker.idNumber().valid())
                            .iin(faker.idNumber().valid())
                            .fileName(faker.file().fileName())
                            .fileType(faker.file().mimeType())
                            .filePath(faker.file().fileName())
                            .type(faker.options().option("Type A", "Type B", "Type C"))
                            .build())
                    .forEach(attachmentRepository::save);

            List<RoleEntity> roles = Stream.of("ROLE_USER", "ROLE_ADMIN")
                    .map(roleName -> RoleEntity.builder()
                            .name(roleName)
                            .build())
                    .toList();
            roleRepository.saveAll(roles);

            List<AnketaEntity> anketaEntities = IntStream.range(0, 5)
                    .mapToObj(i -> AnketaEntity.builder()
                            .iin(faker.idNumber().valid())
                            .previousName(faker.name().fullName())
                            .birthDate(faker.date().birthday().toString())
                            .birthPlace(faker.address().city())
                            .nationality(faker.nation().nationality())
                            .citizenship(faker.nation().nationality())
                            .passportSerie(faker.idNumber().valid())
                            .passportNumber(faker.idNumber().valid())
                            .passportIssuedBy(faker.company().name())
                            .passportIssuedAt(faker.date().birthday().toString())
                            .homePhone(faker.phoneNumber().phoneNumber())
                            .workPhone(faker.phoneNumber().phoneNumber())
                            .relativePhone(faker.phoneNumber().phoneNumber())
                            .relativeFIO(faker.name().fullName())
                            .relativeLevel(faker.job().title())
                            .permanentCity(faker.address().city())
                            .permanentRegion(faker.address().state())
                            .permanentDistrict(faker.address().country())
                            .permanentStreet(faker.address().streetAddress())
                            .permanentHouse(faker.address().buildingNumber())
                            .permanentCorpus(faker.address().secondaryAddress())
                            .permanentApartment(faker.address().buildingNumber())
                            .isAddressMatches(faker.bool().bool())
                            .factualRegion(faker.address().state())
                            .factualDistrict(faker.address().country())
                            .factualStreet(faker.address().streetAddress())
                            .factualHouse(faker.address().buildingNumber())
                            .factualCorpus(faker.address().secondaryAddress())
                            .factualApartment(faker.address().buildingNumber())
                            .educationList(new ArrayList<>())
                            .extracurricularList(new ArrayList<>())
                            .lastThreeWorkplaces(new ArrayList<>())
                            .threeRecommendationPeople(new ArrayList<>())
                            .marriageStatus(faker.options().option("Married", "Single", "Divorced"))
                            .lifeCompanion(new ArrayList<>())
                            .chilrenList(new ArrayList<>())
                            .relativeList(new ArrayList<>())
                            .commercialOrganisationList(new ArrayList<>())
                            .isRelativeJusanEmployee(faker.bool().bool())
                            .relativeJusanEmployeeList(new ArrayList<>())
                            .isCarOwner(faker.bool().bool())
                            .carList(new ArrayList<>())
                            .isMilitary(faker.bool().bool())
                            .isSVC(faker.bool().bool())
                            .isSVCAnswer(faker.options().option("Yes", "No"))
                            .isExpiredLoan(faker.bool().bool())
                            .isExpiredLoanAnswer(faker.options().option("Yes", "No"))
                            .isCriminal(faker.bool().bool())
                            .isCriminalAnswer(faker.options().option("Yes", "No"))
                            .isRelativeCriminal(faker.bool().bool())
                            .isRelativeCriminalAnswer(faker.options().option("Yes", "No"))
                            .isCriminalDelo(faker.bool().bool())
                            .isCriminalDeloAnswer(faker.options().option("Yes", "No"))
                            .isAlimentPayer(faker.bool().bool())
                            .isAlimentPayerAnswer(faker.options().option("Yes", "No"))
                            .isHooligan(faker.bool().bool())
                            .isHooliganAnswer(faker.options().option("Yes", "No"))
                            .additionalInfo(faker.lorem().paragraph())
                            .extraIncome(faker.finance().creditCard())
                            .build())
                    .toList();

            List<UserProfile> userProfiles = IntStream.range(0, 5)
                    .mapToObj(i -> UserProfile.builder()
                            .iin(faker.idNumber().valid())
                            .fio(faker.name().fullName())
                            .mobilePhone(faker.phoneNumber().phoneNumber())
                            .email(faker.internet().emailAddress())
                            .factualCity(faker.address().city())
                            .anketa(anketaEntities.get(i))
                            .build())
                    .toList();


            List<UserEntity> userEntities = IntStream.range(0, 5)
                    .mapToObj(i -> UserEntity.builder()
                            .username(faker.name().username())
                            .password(faker.internet().password())
                            .roleEntity(roles.get(faker.random().nextInt(roles.size())))
                            .userProfile(userProfiles.get(i))
                            .build())
                    .toList();

            userRepository.saveAll(userEntities);
            userRepository.save(UserEntity.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .build());
        };
    }
}

