package cabanas.garcia.ismael.dddinpractice.management.domain.repository;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import org.assertj.core.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.Optional;

public class HeadOfficeRepositoryHappyStub implements HeadOfficeRepository {
    private final HeadOffice headOffice;
    private final double currentBalance;
    private HeadOfficeRepository headOfficeRepository;

    public HeadOfficeRepositoryHappyStub(HeadOffice headOffice, HeadOfficeRepository headOfficeRepository) {
        this.headOffice = headOffice;
        this.headOfficeRepository = headOfficeRepository;
        this.currentBalance = this.headOffice.balance();
    }

    public void verifyUpdateBalanceOfHeadOffice(double amountCharged) {
        ArgumentCaptor<HeadOffice> headOfficeArgumentCaptor = ArgumentCaptor.forClass(HeadOffice.class);
        Mockito.verify(headOfficeRepository, VerificationModeFactory.times(1)).save(headOfficeArgumentCaptor.capture());
        Assertions.assertThat(headOfficeArgumentCaptor.getValue().balance()).isEqualTo(currentBalance + amountCharged);
    }

    @Override
    public Optional<HeadOffice> findById(HeadOfficeId headOfficeId) {
        return Optional.of(headOffice);
    }

    @Override
    public void save(HeadOffice ho) {
        headOfficeRepository.save(ho);
    }
}
