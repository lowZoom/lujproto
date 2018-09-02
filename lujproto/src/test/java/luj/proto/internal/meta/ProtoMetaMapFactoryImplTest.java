package luj.proto.internal.meta;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import luj.proto.internal.meta.spring.ProtoMetaHolder;
import org.junit.Before;
import org.junit.Test;

public class ProtoMetaMapFactoryImplTest {

  ProtoMetaMapFactoryImpl _sut;

  Collection<ProtoMetaHolder<?>> _metaBeans;

  @Before
  public void setUp() {
    _sut = new ProtoMetaMapFactoryImpl();
  }

  @Test
  public void create_拿协议类型() {
    //-- Arrange --//
    MetaMock metaMock = new MetaMock();
    _metaBeans = ImmutableList.of(metaMock);

    //-- Act --//
    ProtoMetaMap result = create();

    //-- Assert --//
    assertThat(result.get(ProtoMock.class)).isNotNull();
  }

  ProtoMetaMap create() {
    return _sut.create(new ProtoMetaMapFactory.BeanMap() {
      @Override
      public Collection<ProtoMetaHolder<?>> getMetaBeans() {
        return _metaBeans;
      }
    });
  }

  class ProtoMock {
    // NOOP
  }

  class MetaMock extends ProtoMetaHolder<ProtoMock> {
    // NOOP
  }
}
