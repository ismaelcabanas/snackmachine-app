package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository;

import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class SnackMachineHappyRepositoryStub implements SnackMachineRepository {
    private final SnackMachine snackMachine;
    private final SnackMachineRepository snackMachineRepositoryMock;

    public SnackMachineHappyRepositoryStub(SnackMachine snackMachine, SnackMachineRepository snackMachineRepositoryMock) {
        this.snackMachine = snackMachine;
        this.snackMachineRepositoryMock = snackMachineRepositoryMock;
    }

    @Override
    public void save(SnackMachine snackMachineToSave) {
        snackMachineRepositoryMock.save(snackMachineToSave);
    }

    @Override
    public Optional<SnackMachine> findById(SnackMachineId snackMachineId) {
        return Optional.of(snackMachine);
    }

    @Override
    public void saveWithSlots(SnackMachine snackMachineToSave) {
        snackMachineRepositoryMock.saveWithSlots(snackMachineToSave);
    }

    public void verifySaveSnackMachineWithNoMoneyInside() {
        ArgumentCaptor<SnackMachine> snackMachineArgCaptor = ArgumentCaptor.forClass(SnackMachine.class);
        verify(snackMachineRepositoryMock, times(1)).save(snackMachineArgCaptor.capture());
        assertThat(snackMachineArgCaptor.getValue().moneyInside()).isEqualTo(Money.none());
    }

    public void verifySaveSnackMachineWithMoney(Money money) {
        ArgumentCaptor<SnackMachine> snackMachineArgCaptor = ArgumentCaptor.forClass(SnackMachine.class);
        verify(snackMachineRepositoryMock, times(1)).save(snackMachineArgCaptor.capture());
        assertThat(snackMachineArgCaptor.getValue().moneyInside()).isEqualTo(money);
    }

    public void verifySaveSnackMachineWithSlotsWithMoney(Money money) {
        ArgumentCaptor<SnackMachine> snackMachineArgCaptor = ArgumentCaptor.forClass(SnackMachine.class);
        verify(snackMachineRepositoryMock, times(1)).saveWithSlots(snackMachineArgCaptor.capture());
        assertThat(snackMachineArgCaptor.getValue().moneyInside()).isEqualTo(money);
    }
}
