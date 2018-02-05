package cabanas.garcia.ismael.dddinpractice.management.domain.repository;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class HeadOfficeRepositoryHappyStub implements HeadOfficeRepository {
    private final HeadOffice headOffice;
    private final double currentBalance;
    private HeadOfficeRepository headOfficeRepositoryMock;

    public HeadOfficeRepositoryHappyStub(HeadOffice headOffice, HeadOfficeRepository headOfficeRepositoryMock) {
        this.headOffice = headOffice;
        this.headOfficeRepositoryMock = headOfficeRepositoryMock;
        this.currentBalance = this.headOffice.balance();
    }

    public void verifyUpdateBalanceOfHeadOffice(double amountCharged) {
        ArgumentCaptor<HeadOffice> headOfficeArgumentCaptor = ArgumentCaptor.forClass(HeadOffice.class);
        Mockito.verify(headOfficeRepositoryMock, VerificationModeFactory.times(1)).save(headOfficeArgumentCaptor.capture());
        assertThat(headOfficeArgumentCaptor.getValue().balance()).isEqualTo(currentBalance + amountCharged);
    }

    @Override
    public Optional<HeadOffice> findById(HeadOfficeId headOfficeId) {
        return Optional.of(headOffice);
    }

    @Override
    public void save(HeadOffice ho) {
        headOfficeRepositoryMock.save(ho);
    }

    public void verifySaveHeadOfficeWithCash(Money cash) {
        ArgumentCaptor<HeadOffice> headOfficeArgCaptor = ArgumentCaptor.forClass(HeadOffice.class);
        verify(headOfficeRepositoryMock, times(1)).save(headOfficeArgCaptor.capture());
        assertThat(headOfficeArgCaptor.getValue().cash()).isEqualTo(cash);
    }

    public void verifySaveHeadOfficeWithoutCash() {
        ArgumentCaptor<HeadOffice> headOfficeArgCaptor = ArgumentCaptor.forClass(HeadOffice.class);
        verify(headOfficeRepositoryMock, times(1)).save(headOfficeArgCaptor.capture());
        assertThat(headOfficeArgCaptor.getValue().cash()).isEqualTo(Money.none());
    }
}
